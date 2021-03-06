package controller;

import java.util.Objects;
import core.mvc.controller.ControllerInitialization;
import model.usuario.Usuario;
import repositories.RepoUsers;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class LogInController extends BaseController {

  private RepoUsers usersRepository = new RepoUsers();

  /* =========================================================== */
  /* Overridables ---------------------------------------------- */
  /* =========================================================== */

  @Override
  protected ControllerInitialization getInitialization() {
    return ControllerInitialization.GET_POST;
  }


  /* =========================================================== */
  /* Lifecycle methods ----------------------------------------- */
  /* =========================================================== */

  @Override
  protected void onInit() {
    // TODO Auto-generated method stub
  }

  @Override
  protected void onBeforeRendering(Request request, Response response) {
    if (isLogged()) {
      navTo(response, "home");
    }
  }

  @Override
  protected void onAfterRendering(Request request, Response response) {
    // Clean view model invalid password class
    getView().getModel().set("isValid", "").set("username", "");
  }

  /* =========================================================== */
  /* Request Handling ------------------------------------------ */
  /* =========================================================== */

  @Override
  protected ModelAndView onPost(Request request, Response response) {

    if (Objects.isNull(request.queryParams("uid"))) {
      onLogIn(request, response);
    } else {
      onLogOut(request, response);
    }

    return super.onPost(request, response);
  }



  /* =========================================================== */
  /* Event Handlers -------------------------------------------- */
  /* =========================================================== */

  /**
   * Log In Event Handler, triggered when an user request POST on the login form.
   * 
   * @param request the spark HTTP request object
   * @param response the spark HTTP response object
   */
  private void onLogIn(Request request, Response response) {

    String username = request.queryParams("user");
    String password = request.queryParams("password");

    if (Objects.isNull(username) || Objects.isNull(password)) {
      response.status(400);
      return;
    }

    Usuario user = usersRepository.getEntity(username, password);

    onAuthenticate(user);

    if (!isLogged()) {
      getView().getModel().set("isValid", "is-invalid").set("username", username);
      response.status(400);
      return;
    }

    request.session().attribute("uid", user.getId());
    response.status(200);
    navTo(response, "home");
  }

  /**
   * Log Out event handler, triggered when an user request POST on a log out form.
   * 
   * @param request the spark HTTP request object
   * @param response the spark HTTP response object
   */
  private void onLogOut(Request request, Response response) {
    onAuthenticate(null);
    request.session().removeAttribute("uid");
    navTo(response, "home");
  }


}
