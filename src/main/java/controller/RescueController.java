package controller;

import static java.lang.Long.parseLong;
import static spark.Spark.post;
import java.util.Arrays;
import java.util.List;
import javax.persistence.NoResultException;
import app.Router;
import model.mascota.Chapita;
import model.mascota.TipoMascota;
import model.mascota.caracteristica.TamanioMascota;
import model.mascota.encontrada.Coordenada;
import model.mascota.encontrada.MascotaEncontrada;
import model.usuario.Rescate;
import model.usuario.Usuario;
import repositories.RepoRescates;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class RescueController extends BaseController {

  @Override
  protected void onInit() {
    post(this.getPath(), (req, res) -> this.onFound(req, res), Router.getEngine());
    this.getModel().put("tipos", TipoMascota.values());
    this.getModel().put("sizes", TamanioMascota.values());
  }

  @Override
  protected void onBeforeRendering(Request request, Response response) {

  }

  @Override
  protected void onAfterRendering(Request request, Response response) {
    onInitToast();
  }

  private ModelAndView onFound(Request request, Response response) {
    String descripcion = request.queryParams("description");
    List<String> fotos = Arrays.asList(request.queryParamsValues("photos"));
    Coordenada coordenada =
        new Coordenada(request.queryParams("positionX"), request.queryParams("positionY"));
    TipoMascota tipoMascota =
        request.queryParams("type").equals("PERRO") ? TipoMascota.PERRO : TipoMascota.GATO;
    TamanioMascota tamanioMascota =
        request.queryParams("size").equals("GRANDE") ? TamanioMascota.GRANDE
            : (request.queryParams("size").equals("MEDIANA") ? TamanioMascota.MEDIANA
                : TamanioMascota.PEQUEÑA);
    long chapitaId =
        parseLong(request.queryParams("tag").equals("") ? "-1" : request.queryParams("tag"));
    Chapita chapita;
    if (chapitaId == -1)
      chapita = null;
    else {
      chapita = new Chapita();
      chapita.setId(chapitaId);

      if (!validarChapita(chapitaId)) {
        // TODO cartel no se encontro mascota con ese numero de chapita
        response.redirect("/rescue");
        onSwitchToast(false);
        return null;
      }

    }
    MascotaEncontrada mascotaEncontrada = new MascotaEncontrada();
    mascotaEncontrada.setChapita(chapita);
    mascotaEncontrada.setDescripcion(descripcion);
    mascotaEncontrada.setLugar(coordenada);
    mascotaEncontrada.setTipoMascota(tipoMascota);
    mascotaEncontrada.setTamanio(tamanioMascota);
    mascotaEncontrada.setFoto(fotos);
    if (this.isLogged()) {
      Usuario user = this.getLoggedUser(request);
      Rescate rescate = new Rescate();
      rescate.setDatosRescatista(user.getDatosPeronales());
      rescate.setMascotaEncontrada(mascotaEncontrada);
      RepoRescates repoRescates = RepoRescates.getInstance();
      withTransaction(() -> repoRescates.addRescate(rescate));
      onSwitchToast(true);
      response.redirect("/");

    } else {
      BaseController.getBaseModel().put("mascotaEncontrada", mascotaEncontrada);
      response.redirect("/contact");
    }
    return null;

  }

  private boolean validarChapita(long chapitaId) {
    try {
      entityManager().createQuery("FROM " + "Chapita" + " C WHERE C.id LIKE :chapitaId ")
          .setParameter("chapitaId", chapitaId).getSingleResult();
      return true;
    } catch (NoResultException exception) {
      return false;
    }
  }
}
