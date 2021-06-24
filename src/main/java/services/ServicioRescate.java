package services;

import model.mascota.encontrada.MascotaEncontrada;
import model.publicacion.Publicacion;
import model.usuario.DuenioMascota;
import model.usuario.Rescatista;
import repositories.RepoPublicaciones;
import repositories.RepoRescates;
import repositories.RepoUsers;

public class ServicioRescate {

  RepoPublicaciones publicaciones = new RepoPublicaciones();
  RepoRescates rescates = new RepoRescates();
  RepoUsers users = new RepoUsers();

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
  public void registrarRescate(Rescatista rescatista) {
    rescates.addRescate(rescatista);
    identificarMascota(rescatista.getMascotaEncontrada());
  }

  /**
   * Realiza la notificacion o publicacion de la mascota encontrada.
   *
   * @param mascota la mascota encontrada
   */
  void identificarMascota(MascotaEncontrada mascota) {
    if (mascota.tieneChapita()) {
      notificarDuenioMascotaPerdida(users.buscarDuenio(mascota));
    } else {
      publicaciones.agregar(new Publicacion(mascota, rescates.buscarAsociacion(mascota)));
    }
  }

  public void notificarDuenioMascotaPerdida(DuenioMascota duenioMascota) {
    duenioMascota.getDatosPeronales().getDatosContacto().getContacto()
        .contactar("Su mascota ha sido encontrada");
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
