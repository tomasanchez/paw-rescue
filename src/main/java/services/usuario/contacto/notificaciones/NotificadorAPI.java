package services.usuario.contacto.notificaciones;

import model.usuario.datospersonales.contacto.DatosContacto;

public interface NotificadorAPI {

  void notificar(DatosContacto contacto, String msg);
  void contactar(DatosContacto contacto1, DatosContacto contacto2, String msg);

}
