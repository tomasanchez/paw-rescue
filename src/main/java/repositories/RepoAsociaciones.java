package repositories;

import java.util.List;

import db.PersistentEntitySet;
import model.mascota.encontrada.MascotaEncontrada;
import model.publicacion.Asociacion;

public class RepoAsociaciones extends PersistentEntitySet<Asociacion> {

  private static RepoAsociaciones instancia;

  public static RepoAsociaciones getInstance() {
    if (instancia == null) {
      instancia = new RepoAsociaciones();
    }
    return instancia;
  }

  public Asociacion buscarAsociacion(MascotaEncontrada mascotaEncontrada) {
    return getEntitySet().isEmpty() ? null : ordenarAsociacionesPorDistancia(mascotaEncontrada).get(0);
  }

  private List<Asociacion> ordenarAsociacionesPorDistancia(MascotaEncontrada mascotaEncontrada) {
    List<Asociacion> asociaciones = getEntitySet();

    asociaciones.sort(
        (asociacion1, asociacion2) -> asociacion1.compararAsociacionesPorDistancia(asociacion2, mascotaEncontrada));

    return asociaciones;
  }

}
