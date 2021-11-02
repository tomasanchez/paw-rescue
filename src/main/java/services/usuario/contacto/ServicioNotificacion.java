package services.usuario.contacto;

import model.publicacion.PublicacionDarEnAdopcion;
import model.usuario.Usuario;
import model.usuario.datospersonales.contacto.DatosContacto;
import repositories.RepoUsers;

public class ServicioNotificacion {

  private static ServicioNotificacion instance;

  public static ServicioNotificacion getInstance() {
    if (instance == null) {
      return new ServicioNotificacion();
    }
    return instance;
  }

  public void contactarDuenioMascotaPerdida(Usuario duenioMascota,
      DatosContacto contactoRescatista) {
    duenioMascota.contactar(contactoRescatista, "Su mascota ha sido encontrada.");
  }

  public void contactarDuenioMascotaAdopcion(Usuario duenioMascota,
      DatosContacto contactoAdopcion) {
    duenioMascota.contactar(contactoAdopcion,
        "Ha aparecido alguien que quiere adoptar su mascota.");
  }

  public void notificarPosibleAdopcion(Usuario usuario, PublicacionDarEnAdopcion publicacion) {
    usuario.notificar("Se han encontrado nuevas posibles adopciones!");
    contactarDuenioMascotaAdopcion(RepoUsers.getInstance().buscarDuenio(publicacion.getMascota()),
        usuario.getDatosPeronales().getDatosContacto());
  }

}
