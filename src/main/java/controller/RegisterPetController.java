package controller;

import app.Router;
import model.mascota.Chapita;
import model.mascota.Mascota;
import model.mascota.Sexo;
import model.mascota.TipoMascota;
import model.mascota.caracteristica.Caracteristica;
import repositories.RepoCaracteristicas;
import services.controller.ControllerService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.Arrays;
import java.util.List;

import static spark.Spark.post;

public class RegisterPetController extends BaseController {

  @Override
  public String getPath() {
    return "/pets/new";
  }

  @Override
  protected void onInit() {
    post(this.getPath(), (req, res) -> this.onPost(req, res), Router.getEngine());
  }

  @Override
  protected void onBeforeRendering(Request request, Response response) {
    requiereSession(request, response);
    this.getModel().put("caracteristicas", RepoCaracteristicas.getInstance().getEntitySet());
    this.getModel().put("tipos", TipoMascota.values());
    this.getModel().put("sexos", Sexo.values());

  }

  @Override
  protected void onAfterRendering(Request request, Response response) {
    this.getModel().put("showToast", false);
  }

  @Override
  protected void requiereSession(Request request, Response response) {
    if (!isLogged(request) || !isLogged()) {
      response.redirect(ControllerService.getInstance().getController("login").getPath());
    }
  }

  private ModelAndView onPost(Request req, Response res) {
    requiereSession(req, res);
    String nombre = req.queryParams("name");
    String apodo = req.queryParams("alias");
    int edad = Integer.parseInt(req.queryParams("age"));
    TipoMascota tipoMascota = TipoMascota.valueOf(req.queryParams("animaltype"));
    Sexo sexo = Sexo.valueOf(req.queryParams("genre"));
    List<String> fotos = Arrays.asList(req.queryParams("photos"));
    String descripcionFisica = req.queryParams("description");
    List<String> caracteristicasTxt = Arrays.asList(req.queryParams("characteristics").split(","));

    try {
      withTransaction(() -> new Mascota(nombre, apodo, tipoMascota, edad, sexo, descripcionFisica,
          fotos, new Chapita()));
      res.status(201);
    } catch (RuntimeException e) {
      res.status(500);
    }
    return this.getViewModel(req, res);
  }

}
