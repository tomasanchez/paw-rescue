package controller;

import app.Router;
import model.mascota.Chapita;
import model.mascota.encontrada.Coordenada;
import model.mascota.encontrada.MascotaEncontrada;
import model.usuario.Rescate;
import model.usuario.Usuario;
import model.usuario.datospersonales.DatosPersonales;
import model.usuario.datospersonales.contacto.Contacto;
import model.usuario.datospersonales.contacto.DatosContacto;
import model.usuario.datospersonales.documento.Documento;
import model.usuario.datospersonales.documento.TipoDocumento;
import repositories.RepoRescates;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import static java.lang.Long.parseLong;
import static spark.Spark.post;

public class ContactController extends BaseController {

  @Override
  protected void onInit() {
    post(this.getPath(), (req, res) -> this.postRescue(req, res), Router.getEngine());
  }

  @Override
  protected void onBeforeRendering(Request request, Response response) {
  }

  @Override
  protected void onAfterRendering(Request request, Response response) {
    // TODO Auto-generated method stub
  }

  private ModelAndView postRescue(Request request, Response response) {
    MascotaEncontrada mascotaEncontrada = (MascotaEncontrada) this.getModel()
      .get("mascotaEncontrada");

    String name = request.queryParams("name");
    String last_name = request.queryParams("last_name");
    String mail = request.queryParams("mail");
    String phone = request.queryParams("phone");
    // TODO validar que tenga mail o telefono
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
