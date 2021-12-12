package controller;

import java.util.Arrays;
import java.util.List;
import core.mvc.controller.ControllerInitialization;
import model.mascota.Mascota;
import model.mascota.Sexo;
import model.mascota.TipoMascota;
import model.mascota.caracteristica.Caracteristica;
import model.usuario.Usuario;
import repositories.RepoCaracteristicas;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class RegisterPetController extends BaseController {


  /* =========================================================== */
  /* Overridables ---------------------------------------------- */
  /* =========================================================== */

  @Override
  public String getEndPoint(Boolean useId) {
    return "/pets/new";
  }

  @Override
  protected ControllerInitialization getInitialization() {
    return ControllerInitialization.GET_POST;
  }

  /* =========================================================== */
  /* Lifecycle methods ----------------------------------------- */
  /* =========================================================== */

  @Override
  protected void onInit() {
    this.getModel().set("tipos", TipoMascota.values());
    this.getModel().set("sexos", Sexo.values());
  }

  @Override
  protected void onBeforeRendering(Request request, Response response) {
    onRequireSession(request, response);
    this.getModel().set("caracteristicas", new RepoCaracteristicas().getEntitySet());
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
    onRequireSession(request, response);
    Usuario user = onRefreshUser();
    onTransactionalOperation(response, () -> user.registrarMascota(buildMascota(request)));

    return super.onPost(request, response);
  }

  /* =========================================================== */
  /* Internal Methods ------------------------------------------ */
  /* =========================================================== */

  /**
   * Obtains a new Pet from request params.
   * 
   * @param req the spark HTTP request object
   * @return a new Pet
   */
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
