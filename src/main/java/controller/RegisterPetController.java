package controller;

import app.Router;
import model.mascota.Chapita;
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

import java.util.Arrays;
import java.util.List;

import static spark.Spark.post;
import static java.lang.Long.parseLong;

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
    String nombre = req.queryParams("name");
    String apodo = req.queryParams("alias");
    int edad = Integer.parseInt(req.queryParams("age"));
    TipoMascota tipoMascota = TipoMascota.valueOf(req.queryParams("animaltype"));
    Sexo sexo = Sexo.valueOf(req.queryParams("genre"));
    List<String> fotos = Arrays.asList(req.queryParamsValues("photos"));
    String descripcionFisica = req.queryParams("description");
    List<String> caracteristicasTxt = Arrays.asList(req.queryParamsValues("characteristics"));
    
    Chapita chapita = new Chapita(user);
    
    try {
      withTransaction(() ->
        user.registrarMascota(
          new Mascota(nombre, apodo, tipoMascota, edad, sexo, descripcionFisica, fotos, chapita)
        )
      );
      
      res.status(201);
    }
    catch (RuntimeException e) {
      entityManager().remove(buscarMascota(chapita.getId()));
      res.status(500);
      this.getModel().put("toastStatus", "bg-error");
      this.getModel().put("toastMessage", getResourceBundle().getText("featureError"));
    }
    setearCaracteristicas(chapita,caracteristicasTxt);
    return this.getViewModel(req, res);
  }

  private void setearCaracteristicas(Chapita chapita, List<String> caracteristicasTxt){
    long id = chapita.getId();
    Mascota pet = buscarMascota(id);
    for(String charId : caracteristicasTxt){
    Caracteristica caracteristica = buscarCaracteristica(parseLong(charId));
    pet.addCaracteristica(caracteristica);
    }
  }
  
  private Caracteristica buscarCaracteristica(long charID) {
      Caracteristica caracteristica = (Caracteristica) entityManager()
        .createQuery("FROM " + "Caracteristica"
          + " C WHERE C.id LIKE :id ")
        .setParameter("id", charID).getSingleResult();
      return caracteristica;
  }

  private Mascota buscarMascota(long chapitaId) {
    Mascota mascota = (Mascota) entityManager()
      .createQuery("FROM " + "Mascota"
        + " M WHERE M.chapita LIKE :id ")
      .setParameter("id", chapitaId).getSingleResult();
    return mascota;
  }


  
}
