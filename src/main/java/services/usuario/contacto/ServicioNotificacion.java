package services.usuario.contacto;

import model.usuario.DuenioMascota;

public class ServicioNotificacion {

  private static ServicioNotificacion instance;

  public static ServicioNotificacion getInstance() {
    if (instance == null) {
      return new ServicioNotificacion();
    }
    return instance;
  }

  public void notificarDuenioMascotaPerdida(DuenioMascota duenioMascota) {
    duenioMascota.notificar("Su mascota ha sido encontrada.");
  }

  public void notificarDuenioMascotaAdopcion(DuenioMascota duenioMascota) {
    duenioMascota.notificar("Ha aparecido alguien que quiere adoptar su mascota.");
  }

}
