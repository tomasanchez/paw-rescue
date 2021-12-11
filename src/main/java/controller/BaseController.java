package controller;

import static spark.Spark.halt;
import java.util.Map;
import java.util.Objects;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import core.mvc.controller.Controller;
import core.services.ControllerLoaderService;
import model.usuario.Privilegio;
import model.usuario.Usuario;
import repositories.RepoUsers;
import spark.Request;
import spark.Response;

public abstract class BaseController extends Controller
    implements WithGlobalEntityManager, TransactionalOps {


  /* =========================================================== */
  /* LifeCycle Static BaseController --------------------------- */
  /* =========================================================== */

  /**
   * Static method for initialization of BaseController.
   */
  public static void initBaseController() {
    onAuthenticate(null);
    getSharedModel().set("showToast", false);
  }

  /* =========================================================== */
  /* LifeCycle Overrides --------------------------------------- */
  /* =========================================================== */

  @Override
  protected void onAfterAfterRendering(Request request, Response response) {
    onCleanToast();
  }

  /* =========================================================== */
  /* Convenience Methods --------------------------------------- */
  /* =========================================================== */

  /**
   * Convenience method for getting the view model map.
   * 
   * @return the View Model
   */
  public Map<String, Object> getModel() {
    return this.getView().getModel().getData();
  }

  /**
   * Convenience method for getting a value from i18n.
   * 
   * @param text the key value
   * @return the text value of the key
   */
  public String getText(String text) {
    return (String) Controller.getI18n().getText(text);
  }

  /**
   * Navigates to the specified location.
   * 
   * @param response the spark HTTP response object
   * @param location an endpoint, a controller name or a path
   */
  public void navTo(Response response, String location) {

    if (location.startsWith("/")) {
      response.redirect(location);
      return;
    }

    Controller c = ControllerLoaderService.getService().find(location);
    response.redirect(Objects.isNull(c) ? "/".concat(location) : c.getEndPoint());

  }

  /**
   * Reloads the current view.
   * 
   * @param response the spark HTTP response object
   */
  protected void onRefreshView(Response response) {
    navTo(response, this.getEndPoint());
  }

  /**
   * Tries a transactional operation activing corresponding toasts.
   * 
   * @param response the spark HTTP response object
   * @param operation the transactional operation
   */
  protected boolean onTransactionalOperation(Response response, Runnable operation) {
    try {
      withTransaction(operation);
      onSwitchToast(true);
      response.status(201);
      return true;
    } catch (RuntimeException e) {
      response.status(500);
      onSwitchToast(false);
      return false;
    }
  }

  /* =========================================================== */
  /* Authentification ------------------------------------------ */
  /* =========================================================== */

  /**
   * Sets Usuario into the shared model.
   * 
   * @param user the user to be set
   */
  protected static void onAuthenticate(Usuario user) {
    getSharedModel().set("user", user).set("loggedIn", !Objects.isNull(user))
        .set("isAdmin",
            Objects.isNull(user) ? false : user.getPrivileges().equals(Privilegio.ADMIN))
        .set("userPrivilege", Objects.isNull(user) ? 0 : user.getPrivileges().ordinal());
  }

  /**
   * Reloads user data from the database.
   * 
   * @return the user updated.
   */
  protected Usuario onRefreshUser() {
    Usuario refreshed = new RepoUsers().getEntity(((Usuario) getSharedModel().get("user")).getId());
    getSharedModel().set("user", refreshed);
    return refreshed;
  }


  /**
   * Verifies if an user is loaded into the shared view model.
   * 
   * @return wheter there is an user or not
   */
  protected boolean isLogged() {
    return !Objects.isNull(getSharedModel().get("user"));
  }

  /**
   * Verifies if a session is stablished in the request.
   * 
   * @param request the Spark HTTP
   * @return wheter there is a session or not
   */
  protected boolean isLogged(Request request) {
    return !Objects.isNull(request.session().attribute("uid"));
  }

  /**
   * Verifies if session is active in the request and model.
   * 
   * <br>
   * </br>
   * 
   * ? If not logged in, redirects to 401
   * 
   * @param request the Spark HTTP request object
   * @param response the Spark HTTP response object
   */
  protected void onRequireSession(Request request, Response response) {
    if (!isLogged(request) || !isLogged()) {
      onHaltLogInNeeded(response);
    }
  }


  /**
   * Verifies if session is active in the request and model AND corresponds to an Admin session.
   * 
   * <br>
   * </br>
   * 
   * ? If not Admin, redirects to 402
   * 
   * @param request the Spark HTTP request object
   * @param response the Spark HTTP response object
   */
  protected void onRequirePrivileges(Request request, Response response) {

    Usuario user = (Usuario) getSharedModel().get("user");

    if (Objects.isNull(user)) {
      onRequireSession(request, response);
      return;
    }

    if (!user.getPrivileges().equals(Privilegio.ADMIN)) {
      onHaltAdminNeeded(response);
    }
  }
  /* =========================================================== */
  /* Toasts Handling ------------------------------------------- */
  /* =========================================================== */

  /**
   * Intercala el toast message con mensajes de error y éxito.
   * 
   * @param status true para mostrar exito, false para mostrar error.
   */
  protected void onSwitchToast(boolean status) {
    this.getModel().put("showToast", true);
    this.getModel().put("toastStatus", status ? "bg-success" : "bg-danger");
    this.getModel().put("toastMessage",
        status ? getText("featureSuccess") : getText("featureError"));
  }

  protected void onCleanToast() {
    this.getModel().put("showToast", false);
  }

  /* =========================================================== */
  /* Private methods ------------------------------------------- */
  /* =========================================================== */

  /**
   * 
   * Halts and redirects to Log In page (401).
   * 
   * @param response the Spark HTTP response object
   */
  private void onHaltLogInNeeded(Response response) {
    halt(401);
    response.redirect(ControllerLoaderService.getService().find("login").getEndPoint(), 401);
  }

  /**
   * Halts and redirects to Home 402.
   * 
   * @param response the Spark HTTP response object
   */
  private void onHaltAdminNeeded(Response response) {
    halt(402);
    response.redirect(ControllerLoaderService.getService().find("home").getEndPoint(), 402);
  }

}
