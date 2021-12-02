package controller;

import static spark.Spark.post;
import app.Router;
import model.mascota.encontrada.MascotaEncontrada;
import model.usuario.Rescate;
import model.usuario.datospersonales.DatosPersonales;
import model.usuario.datospersonales.contacto.DatosContacto;
import repositories.RepoRescates;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ContactController extends BaseController {

  @Override
  protected void onInit() {
    post(this.getPath(), (req, res) -> this.postRescue(req, res), Router.getEngine());
  }

  @Override
  protected void onBeforeRendering(Request request, Response response) {}

  @Override
  protected void onAfterRendering(Request request, Response response) {
    // TODO Auto-generated method stub
  }

  private ModelAndView postRescue(Request request, Response response) {
    MascotaEncontrada mascotaEncontrada =
        (MascotaEncontrada) this.getModel().get("mascotaEncontrada");

    String name = request.queryParams("name");
    String last_name = request.queryParams("last_name");
    String mail = request.queryParams("mail");
    String phone = request.queryParams("phone");

    if (mail.equals("") && phone.equals("")) {
      // TODO cartel debe completar telefono o mail
      BaseController.getBaseModel().put("mascotaEncontrada", mascotaEncontrada);
      response.redirect("/contact");
      return null;
    }
    DatosContacto datosContacto = new DatosContacto(name, last_name, phone, mail);

    DatosPersonales datosPersonales = new DatosPersonales(datosContacto);

    Rescate rescate = new Rescate();
    rescate.setDatosRescatista(datosPersonales);
    rescate.setMascotaEncontrada(mascotaEncontrada);
    RepoRescates repoRescates = RepoRescates.getInstance();
    withTransaction(() -> repoRescates.addRescate(rescate));

    response.redirect("/");
    return null;

  }

}
