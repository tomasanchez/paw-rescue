package controller;

import static spark.Spark.after;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import i18n.ResourceBundle;
import model.usuario.Privilegio;
import model.usuario.Usuario;
import repositories.RepoUsers;
import services.controller.ControllerService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public abstract class BaseController implements WithGlobalEntityManager, TransactionalOps {

  /**
   * View Model.
   */
  private Map<String, Object> model = new HashMap<String, Object>();
  private static AppController appController = new AppController();

  @SuppressWarnings("unchecked")
  BaseController() {
    this.onInit();
    this.onInitToast();
    ((Map<String, Object>) getBaseModel().get("navigation")).put(this.getControllerName(), "");
    ((Map<String, Object>) getBaseModel().get("hrefs")).put(this.getControllerName(),
        this.getPath());
    after(this.getPath(), (req, res) -> onAfterRendering(req, res));
  }

  /**
   * Obtiene el modelo compartido entre todos los controllers.
   * 
   * @return el modelo compartido
   */
  public static Map<String, Object> getBaseModel() {
    return appController.getAppModel();
  }

  /**
   * Obtiene el bunlde de internacionalizacion.
   * 
   * @return el i18n resource bundle
   */
  public static ResourceBundle getResourceBundle() {
    return appController.getResourceBundle();
  }

  /**
   * Obtiene el nombre del controlador.
   * 
   * ? Ej: HomeController => home
   * 
   * @return el nombre del controller
   */
  public String getControllerName() {
    return getClass().getSimpleName().toLowerCase().replace("controller", "");
  }

  /**
   * Obtiene el nombre del archivo de la vista.
   * 
   * ? Ej: HomeController => Home.hbs.html
   * 
   * @return el View Name
   */
  public String getViewName() {
    return this.getControllerName().concat(".html.hbs");
  }

  /**
   * Obtiene el HTTP path.
   * 
   * ? Ej: HomeController => /Home
   * 
   * @return
   */
  public String getPath() {
    return "/".concat(this.getClass().getSimpleName().toLowerCase().replace("controller", ""));
  }

  public Map<String, Object> getModel() {
    return model;
  }

  public void setModel(Map<String, Object> model) {
    this.model = model;
  }

  /**
   * Obtiene la ruta del viewmodel.
   * 
   * @param request la HTTP request
   * @param response la HTTP response
   * @return el ViewModel
   */
  public ModelAndView getViewModel(Request request, Response response) {
    checkLogUser(request);

    appController.updateLanguage(request);
    appController.updateNavigationModel(this.getControllerName());

    checkRedirects(request, response);

    onBeforeRendering(request, response);
    getModel().putAll(getBaseModel());
    return new ModelAndView(this.getModel(), this.getViewName());
  }

  /**
   * Redibuja un ModelAndView.
   * 
   * @return el nuevo model and view;
   */
  public ModelAndView getViewModel() {
    this.getModel().putAll(getBaseModel());
    return new ModelAndView(this.getModel(), this.getViewName());
  }

  private void checkRedirects(Request request, Response response) {
    String previousHash = (String) getBaseModel().get("previous");
    if (!Objects.isNull(previousHash) && !request.matchedPath()
        .equals(ControllerService.getInstance().getController("login").getPath())) {
      response.redirect(previousHash);
      getBaseModel().put("previous", null);
    }
  }

  private void checkLogUser(Request request) {
    getBaseModel().replace("loggedIn", isLogged(request));

    // Si está loggeado y no cargamos el usuario
    if (this.isLogged() && Objects.isNull(getBaseModel().get("user"))) {
      Usuario user = this.getLoggedUser(request);
      boolean hasPrivilege = Objects.isNull(user.getPrivileges());
      getBaseModel().put("user", user);
      getBaseModel().put("userPrivilege",
          hasPrivilege ? 0 : ((Usuario) getBaseModel().get("user")).getPrivileges().ordinal());
      getBaseModel().put("isAdmin",
          (Integer) getBaseModel().get("userPrivilege") == Privilegio.ADMIN.ordinal());
      getBaseModel().put("isVoluntario",
          (Integer) getBaseModel().get("userPrivilege") == Privilegio.VOLUNTARIO.ordinal());

    }
  }

  /**
   * Funcion llamada por única vez, antes de crear el controllador.
   */
  protected abstract void onInit();

  /**
   * Funcion llamada siempre antes de renderizar la Vista.
   * 
   * @param request la HTTP request.
   * @param response la HTTP response.
   */
  protected abstract void onBeforeRendering(Request request, Response response);

  /**
   * Método llamado despues de renderizar la vista.
   * 
   * @param request la spark request
   * @param response la spark response
   */
  protected abstract void onAfterRendering(Request request, Response response);

  /**
   * Inicializa el toast message, ocultandolo.
   */
  protected void onInitToast() {
    this.getModel().put("showToast", false);
  }

  /**
   * Intercala el toast message con mensajes de error y éxito.
   * 
   * @param status true para mostrar exito, false para mostrar error.
   */
  protected void onSwitchToast(boolean status) {
    this.getModel().put("showToast", true);
    this.getModel().put("toastStatus", status ? "bg-success" : "bg-danger");
    this.getModel().put("toastMessage", status ? getResourceBundle().getText("featureSuccess")
        : getResourceBundle().getText("featureError"));
  }

  /**
   * Obtiene el usuario loggeado de una session.
   * 
   * @param request la request con el usuario loggedo
   * @return
   */
  protected Usuario getLoggedUser(Request request) {
    return RepoUsers.getInstance().getEntity(request.session().attribute("uid"));
  }


  protected Usuario onRefreshUser() {
    Usuario user = (Usuario) getBaseModel().get("user");
    Usuario refreshed = RepoUsers.getInstance().getEntity(user.getId());
    getBaseModel().put("user", refreshed);
    return refreshed;
  }

  /**
   * Chequea si hay un usuario loggeado.
   * 
   * @param request
   * @return
   */
  protected boolean isLogged(Request request) {
    return !Objects.isNull(request.session().attribute("uid"));
  }

  protected boolean isLogged() {
    return (boolean) getBaseModel().get("loggedIn");
  }

  protected void requiereSession(Request request, Response response) {
    if (!isLogged(request) || !isLogged()) {
      getBaseModel().put("previous", request.matchedPath());
      response.redirect(ControllerService.getInstance().getController("login").getPath(), 401);
    }
  }

  protected void requireAdmin(Request request, Response response) {
    if (!(boolean) getBaseModel().get("isAdmin")) {
      response.redirect(ControllerService.getInstance().getController("home").getPath(), 403);
    }
  }

  protected void onRefreshView(Request request, Response response) {
    response.redirect(this.getPath());
  }
}
