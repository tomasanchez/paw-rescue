package services.usuario.contacto.notificaciones;

import model.usuario.datospersonales.contacto.DatosContacto;

public abstract class NotificadorAPI {

  public void notificar(DatosContacto contacto, String msg){
    
  };

  public void contactar(DatosContacto contacto1, DatosContacto contacto2, String msg){
    notificar(contacto1,
      msg + " Por favor ponganse en contacto con  " + contacto2.getNombre()
        + " mediante su telefono (" + contacto2.getTelefono() + ") o su correo ("
        + contacto2.getMail() + ")");
  };

}
