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
    Spark.post(this.getPath(), (req, res) -> this.onLogin(req, res), Router.getEngine());
  }

  private ModelAndView onLogin(Request request, Response response) {

    String uname = request.queryParams("user");
    String password = request.queryParams("password");

    if (Objects.isNull(uname) || Objects.isNull(password)) {
      response.status(400);
      return null;
    }

    Usuario user = RepoUsers.getInstance().getLogin(uname, password);

    this.getModel().put("username", uname);
    this.getModel().put("isValid", (Objects.isNull(user) ? "is-invalid" : "is-valid"));

    return this.getViewModel();
  }



}
