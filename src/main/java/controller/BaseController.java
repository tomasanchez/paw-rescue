package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import i18n.ResourceBundle;
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
  private static volatile ResourceBundle resourceBundle = new ResourceBundle();
  private static volatile Map<String, Object> baseModel;

  BaseController() {
    this.onInit();
  }

  /**
   * Obtiene el modelo compartido entre todos los controllers.
   * 
   * @return el modelo compartido
   */
  public static Map<String, Object> getBaseModel() {
    if (Objects.isNull(baseModel)) {
      baseModel = new HashMap<String, Object>();
    }
    return baseModel;
  }

  /**
   * Obtiene el bunlde de internacionalizacion.
   * 
   * @return el i18n resource bundle
   */
  public static ResourceBundle getResourceBundle() {
    return resourceBundle;
  }

  /**
   * Obtiene el nombre del controlador.
   * 
   * ? Ej: HomeController => Home
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
    this.checkLogUser(request);

    if (Objects.isNull(getBaseModel().get("language"))) {
      resourceBundle.updateMap(request.headers("Accept-Language"), getBaseModel());
    } else {
      resourceBundle.updateMap(String.valueOf(getBaseModel().get("language")), getBaseModel());
    }

    this.onBeforeRendering(request, response);
    this.getModel().putAll(getBaseModel());
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

  public static void initBaseModel() {
    getBaseModel().put("loggedIn", false);
    getBaseModel().put("user", null);
    getBaseModel().put("userPrivilege", 0);
    getBaseModel().put("language", null);
    getBaseModel().put("i18n", resourceBundle.getModel());
  }

  private void checkLogUser(Request request) {
    getBaseModel().replace("loggedIn", isLogged(request));

    if (this.isLogged()) {
      Usuario user = this.getLoggedUser(request);
      boolean hasPrivilege = Objects.isNull(user.getPrivileges());
      getBaseModel().put("userPrivilege", hasPrivilege ? 0 : user.getPrivileges().ordinal());
      getBaseModel().put("user", user);
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

  protected Usuario getLoggedUser(Request request) {
    return RepoUsers.getInstance().getEntity(request.session().attribute("uid"));
  }

  protected void requiereSession(Request request, Response response) {
    if (!isLogged(request) || !isLogged()) {
      response.status(401);
      response.redirect(ControllerService.getInstance().getController("login").getPath());
    }
  }

  protected void onRefreshView(Response response) {
    response.redirect(this.getPath());
  }
}
