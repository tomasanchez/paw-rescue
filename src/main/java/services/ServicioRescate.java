package services;

import model.mascota.Chapita;
import model.mascota.encontrada.MascotaEncontrada;
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

  public RepoPublicaciones getPublicaciones() {
    return publicaciones;
  }

  public ServicioRescate setPublicaciones(RepoPublicaciones publicaciones) {
    this.publicaciones = publicaciones;
    return this;
  }

  public RepoRescates getRescates() {
    return rescates;
  }

  public ServicioRescate setRescates(RepoRescates rescates) {
    this.rescates = rescates;
    return this;
  }


  /**
   * Realiza la notificacion o publicacion de la mascota encontrada.
   * 
   * @param mascota la mascota encontrada
   */
  public void identificarMascota(MascotaEncontrada mascota) {

    if (mascota.tieneChapita()) {
      notificarDuenioMascotaPerdida(mascota.getChapita());
    } else {
    }

  }

  public void notificarDuenioMascotaPerdida(Chapita chapita) {
    chapita.getDuenio().getDatosPeronales().getDatosContacto().getContacto()
        .contactar("Su mascota ha sido encontrada");
  }

}
