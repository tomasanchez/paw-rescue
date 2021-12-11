package controller;

import core.mvc.controller.ControllerInitialization;
import model.mascota.encontrada.MascotaEncontrada;
import model.usuario.Rescate;
import model.usuario.datospersonales.DatosPersonales;
import model.usuario.datospersonales.contacto.DatosContacto;
import repositories.RepoRescates;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ContactController extends BaseController {


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
    // TODO Auto-generated method stub

  }

  @Override
  protected void onAfterRendering(Request request, Response response) {
    // TODO Auto-generated method stub

  }

  /* =========================================================== */
  /* Request Handling ------------------------------------------ */
  /* =========================================================== */

  @Override
  protected ModelAndView onPost(Request request, Response response) {

    MascotaEncontrada mascotaEncontrada =
        (MascotaEncontrada) this.getModel().get("mascotaEncontrada");

    String name = request.queryParams("name");
    String last_name = request.queryParams("last_name");
    String mail = request.queryParams("mail");
    String phone = request.queryParams("phone");

    DatosContacto datosContacto = new DatosContacto(name, last_name, phone, mail);

    DatosPersonales datosPersonales = new DatosPersonales(datosContacto);

    Rescate rescate = new Rescate();
    rescate.setDatosRescatista(datosPersonales);
    rescate.setMascotaEncontrada(mascotaEncontrada);
    RepoRescates repoRescates = RepoRescates.getInstance();

    onTransactionalOperation(response, () -> repoRescates.addRescate(rescate));

    navTo(response, "home");

    return null;
  }



}
