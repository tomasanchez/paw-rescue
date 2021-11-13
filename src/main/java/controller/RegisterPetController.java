package controller;

import static spark.Spark.post;
import java.util.Arrays;
import java.util.List;
import app.Router;
import model.mascota.Mascota;
import model.mascota.Sexo;
import model.mascota.TipoMascota;
import model.mascota.caracteristica.Caracteristica;
import model.usuario.Usuario;
import repositories.RepoCaracteristicas;
import services.controller.ControllerService;
import spark.ModelAndView;
import spark.Request;
import spark.Response;


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
    Usuario user = this.getLoggedUser(req);

    try {
      withTransaction(() -> user.registrarMascota(buildMascota(req)));
      res.status(201);
      res.redirect(ControllerService.getInstance().getController("pets").getPath());
    } catch (RuntimeException e) {
      res.status(500);
      this.getModel().put("toastStatus", "bg-error");
      this.getModel().put("toastMessage", getResourceBundle().getText("featureError"));
    }
    return this.getViewModel(req, res);
  }

  private Mascota buildMascota(Request req) {
    String nombre = req.queryParams("name");
    String apodo = req.queryParams("alias");
    int edad = Integer.parseInt(req.queryParams("age"));
    TipoMascota tipoMascota = TipoMascota.valueOf(req.queryParams("animaltype"));
    Sexo sexo = Sexo.valueOf(req.queryParams("genre"));
    List<String> fotos = Arrays.asList(req.queryParamsValues("photos"));
    String descripcionFisica = req.queryParams("description");
    List<String> caracteristicasTxt = Arrays.asList(req.queryParamsValues("characteristics"));
    List<Caracteristica> caracteristicas =
        RepoCaracteristicas.getInstance().getEntitySet(caracteristicasTxt);
    Mascota mascota =
        new Mascota(nombre, apodo, tipoMascota, edad, sexo, descripcionFisica, fotos, null);
    mascota.getCaracteristicas().addAll(caracteristicas);
    return mascota;
  }
}
