package services.usuario.contacto.notificaciones;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import model.usuario.datospersonales.contacto.DatosContacto;
import tools.configuracion.Configuracion;

public class NotificadorSMS extends NotificadorAPI {

  @Override
  public void notificar(DatosContacto contacto, String msg) {
    String acc = new Configuracion().getProperty("TW_SID");
    String token = new Configuracion().getProperty("TW_TOKEN");
    String telefono = new Configuracion().getProperty("TW_TEL");

    Twilio.init(acc, token);

    Message.creator(new PhoneNumber("+" + contacto.getTelefono()), new PhoneNumber(telefono), msg)
        .create();
  }
  
}
