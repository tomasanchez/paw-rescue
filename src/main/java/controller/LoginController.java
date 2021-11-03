package controller;

import java.util.Objects;
import app.Router;
import model.usuario.Usuario;
import repositories.RepoUsers;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;

public class LoginController extends BaseController {

  @Override
  protected void onInit(Request request, Response response) {

    if (this.isLogged()) {
      response.redirect("/");
      return;
    }

    Spark.post(this.getPath(), (req, res) -> this.onSession(req, res), Router.getEngine());
  }

  private ModelAndView onSession(Request request, Response response) {

    if (Objects.isNull(request.queryParams("uid"))) {
      return this.onLogIn(request, response);
    } else {
      return this.onLogOut(request, response);
    }

  }

  public ModelAndView onLogOut(Request request, Response response) {
    BaseController.getBaseModel().replace("loggedIn", false);
    this.getModel().replace("isValid", null);
    request.session().removeAttribute("uid");
    return this.getViewModel();
  }

  private ModelAndView onLogIn(Request request, Response response) {
    String uname = request.queryParams("user");
    String password = request.queryParams("password");

    if (Objects.isNull(uname) || Objects.isNull(password)) {
      response.status(400);
      return null;
    }

    Usuario user = RepoUsers.getInstance().getLogin(uname, password);
    boolean hasLogin = !Objects.isNull(user);


    this.getModel().put("username", uname);
    this.getModel().put("isValid", (!hasLogin ? "is-invalid" : "is-valid"));

    if (hasLogin) {
      BaseController.getBaseModel().put("loggedIn", true);
      request.session().attribute("uid", user.getId());
      response.redirect("/");
      return null;
    }

    return this.getViewModel();
  }



}
