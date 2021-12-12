package controller;

import java.time.LocalDate;
import core.mvc.controller.ControllerInitialization;
import exceptions.acceso.InvalidPasswordException;
import exceptions.usuario.UsuarioYaExisteException;
import model.usuario.Privilegio;
import model.usuario.Usuario;
import model.usuario.datospersonales.DatosPersonales;
import model.usuario.datospersonales.documento.Documento;
import model.usuario.datospersonales.documento.TipoDocumento;
import repositories.RepoUsers;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import tools.password.ValidadorContrasenia;

public class SignUpController extends BaseController {

  private ValidadorContrasenia validator;

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
    validator = new ValidadorContrasenia();
    this.getModel().set("minLength", validator.getMinLength());
    this.getModel().set("tiposDocs", TipoDocumento.values());
  }

  @Override
  protected void onBeforeRendering(Request request, Response response) {
    // TODO Auto-generated method stub

  }

  @Override
  protected void onAfterRendering(Request request, Response response) {
    // Limpio los valores que se pusieron antes
    getModel().set("user-valid", "").set("pw-valid", "");
  }


  /* =========================================================== */
  /* Request Handling ------------------------------------------ */
  /* =========================================================== */

  @Override
  protected ModelAndView onPost(Request request, Response response) {
    Usuario user;

    try {
      user = buildUser(request);
      if (onTransactionalOperation(response, () -> RepoUsers.getInstance().createEntity(user))) {
        navTo(response, "login");
      }
    } catch (InvalidPasswordException e) {
      getModel().set("pw-valid", "is-invalid");
    } catch (UsuarioYaExisteException e) {
      getModel().set("user-valid", "is-invalid");
    }

    return super.onPost(request, response);
  }

  /* =========================================================== */
  /* Internal Methods ------------------------------------------ */
  /* =========================================================== */

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
