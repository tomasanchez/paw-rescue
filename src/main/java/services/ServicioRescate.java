package services;

import model.mascota.encontrada.MascotaEncontrada;
import model.publicacion.PublicacionRescate;

import model.usuario.Rescate;
import repositories.RepoAsociaciones;
import repositories.RepoPublicaciones;
import repositories.RepoRescates;
import repositories.RepoUsers;
import services.usuario.contacto.ServicioNotificacion;

public class ServicioRescate {

  private RepoPublicaciones publicaciones;
  private RepoRescates rescates;
  private RepoUsers users;

  public ServicioRescate(RepoPublicaciones publicaciones, RepoRescates rescates, RepoUsers users) {
    this.publicaciones = publicaciones;
    this.rescates = rescates;
    this.users = users;
  }

  /**
   * Guarda la información de una mascota rescatada.
   *
   * @param rescatista el rescatista rescatada
   */
  public void registrarRescate(Rescate rescatista) {
    rescates.addRescate(rescatista);
    identificarMascota(rescatista.getMascotaEncontrada());
  }

  /**
   * Realiza la notificacion o publicacion de la mascota encontrada.
   *
   * @param mascota la mascota encontrada
   */
  public void identificarMascota(MascotaEncontrada mascota) {
    if (mascota.tieneChapita()) {
      ServicioNotificacion.getInstance().
        notificarDuenioMascotaPerdida(users.buscarDuenio(mascota));
    } else {
      publicaciones.agregar(new PublicacionRescate(mascota, RepoAsociaciones.getInstance().buscarAsociacion(mascota)));
    }
  }

  public RepoRescates getRescates() {
    return rescates;
  }

  public RepoPublicaciones getPublicaciones() {
    return publicaciones;
  }

  public ServicioRescate setPublicaciones(RepoPublicaciones publicaciones) {
    this.publicaciones = publicaciones;
    return this;
  }

  public ServicioRescate setRescates(RepoRescates rescates) {
    this.rescates = rescates;
    return this;
  }

}
