package services;

import model.mascota.encontrada.MascotaEncontrada;
import model.publicacion.PublicacionRescate;
import model.usuario.Rescate;
import model.usuario.datospersonales.contacto.DatosContacto;
import repositories.RepoAsociaciones;
import repositories.RepoPubRescate;
import repositories.RepoRescates;
import repositories.RepoUsers;
import services.usuario.contacto.ServicioNotificacion;

public class ServicioRescate {

  private RepoPubRescate publicaciones = new RepoPubRescate();
  private RepoRescates rescates = new RepoRescates();
  private RepoUsers users = new RepoUsers();

  public ServicioRescate(RepoPubRescate publicaciones, RepoRescates rescates, RepoUsers users) {
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
    identificarMascota(rescatista.getMascotaEncontrada(), rescatista.getDatosRescatista().getDatosContacto());
  }

  /**
   * Realiza la notificacion o publicacion de la mascota encontrada.
   *
   * @param mascota la mascota encontrada
   */
  public void identificarMascota(MascotaEncontrada mascota, DatosContacto datosContacto) {
    if (mascota.tieneChapita()) {
      ServicioNotificacion.getInstance().contactarDuenioMascotaPerdida(
        users.buscarDuenio(mascota),
        datosContacto);
    }
    else {
      publicaciones
          .createEntity(new PublicacionRescate(mascota, RepoAsociaciones.getInstance().buscarAsociacion(mascota)));
    }
  }

  public RepoRescates getRescates() {
    return rescates;
  }

  public RepoPubRescate getPublicaciones() {
    return publicaciones;
  }

  public ServicioRescate setPublicaciones(RepoPubRescate publicaciones) {
    this.publicaciones = publicaciones;
    return this;
  }

  public ServicioRescate setRescates(RepoRescates rescates) {
    this.rescates = rescates;
    return this;
  }

}
