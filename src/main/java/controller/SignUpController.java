package controller;

import static spark.Spark.post;
import java.time.LocalDate;
import app.Router;
import exceptions.acceso.InvalidPasswordException;
import exceptions.usuario.UsuarioYaExisteException;
import model.usuario.Privilegio;
import model.usuario.Usuario;
import model.usuario.datospersonales.DatosPersonales;
import model.usuario.datospersonales.documento.Documento;
import model.usuario.datospersonales.documento.TipoDocumento;
import repositories.RepoUsers;
import services.controller.ControllerService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import tools.password.ValidadorContrasenia;

public class SignUpController extends BaseController {

  private ValidadorContrasenia validator;


  @Override
  protected void onInit() {
    validator = new ValidadorContrasenia();
    post(this.getPath(), (req, res) -> this.onSignUp(req, res), Router.getEngine());

    // Inicializo el View Model.
    this.getModel().put("minLength", validator.getMinLength());
    this.getModel().put("tiposDocs", TipoDocumento.values());
  }



  @Override
  protected void onBeforeRendering(Request request, Response response) {}

  @Override
  protected void onAfterRendering(Request request, Response response) {
    // Limpio los valores que se pusieron antes
    getModel().put("user-valid", "");
    getModel().put("pw-valid", "");
  }

  private ModelAndView onSignUp(Request req, Response res) {

    Usuario user;

    try {
      user = buildUser(req);
      withTransaction(() -> RepoUsers.getInstance().createEntity(user));
      res.redirect(ControllerService.getInstance().getController("login").getPath());
    } catch (InvalidPasswordException e) {
      getModel().put("pw-valid", "is-invalid");
    } catch (UsuarioYaExisteException e) {
      getModel().put("user-valid", "is-invalid");
    }

    return getViewModel();
  }

  private Usuario buildUser(Request req) {

    Usuario user = new Usuario();
    user.setUsuario(req.queryParams("uname")).setPassword(req.queryParams("password"));

    new ValidadorContrasenia().validarPassword(user.getUsuario(), user.getPassword());


    // TipoDocumento.values()[Integer.valueOf(req.queryParams("typeId"))]

    Documento doc = new Documento(TipoDocumento.valueOf(req.queryParams("typeId")),
        Integer.parseInt(req.queryParams("id")));

    user.setPrivilege(Privilegio.OWNER_MASCOTA)
        .setDatosPersonales(new DatosPersonales(req.queryParams("name"),
            req.queryParams("lastName"), doc, req.queryParams("email"), req.queryParams("phone"),
            LocalDate.parse(req.queryParams("date"))));

    return user;
  }

}
