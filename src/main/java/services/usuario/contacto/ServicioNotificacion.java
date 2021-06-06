package services.usuario.contacto;

import java.util.Properties;

public class ServicioNotificacion {

  Properties properties = new Properties();

  public ServicioNotificacion() {
    initProperties();
  }

  private ServicioNotificacion initProperties() {
    properties.put("mail.smpt.auth", "true");
    properties.put("mail.smpt.starttls.enable", "true");
    properties.put("mail.smpt.host", "smtp.gmail.com");
    properties.put("mail.smpt.port", "587");
    return this;
  }

  public void sendMail() {}

}
