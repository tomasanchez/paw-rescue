package services.usuario.contacto.notificaciones;


import model.usuario.datospersonales.contacto.DatosContacto;
import tools.configuracion.Configuracion;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class NotificadorMail implements NotificadorAPI {


  @Override
  public void notificar(DatosContacto contacto, String msg) {

    String remitente = new Configuracion().getProperty("mail_user");
    String correoDestinatario = contacto.getMail();
    String host = new Configuracion().getProperty("mail_host");
    String clave = new Configuracion().getProperty("mail_clav");
    InternetAddress direccion = null;
    try {
      direccion = new InternetAddress(correoDestinatario);
    } catch (AddressException e) {
      e.printStackTrace();
    }


    Properties props = System.getProperties();
    props.put("mail.smtp.host", host);
    props.put("mail.smtp.user", remitente);
    props.put("mail.smtp.clave", clave);
    props.put("mail.smtp.auth", new Configuracion().getProperty("mail_auth"));
    props.put("mail.smtp.starttls.enable", new Configuracion().getProperty("mail_enable"));
    props.put("mail.smtp.port", new Configuracion().getProperty("mail_port"));
    props.put("mail.smtp.ssl.trust", new Configuracion().getProperty("mail_trust"));

    Session session = Session.getDefaultInstance(props);
    MimeMessage message = new MimeMessage(session);

    try {
      message.setFrom(new InternetAddress(remitente));
      message.addRecipient(Message.RecipientType.TO, direccion); // Se pueden añadir varios de la
                                                                 // misma manera
      message.setSubject("Hay novedades!!");
      message.setText(msg);
      Transport transport = session.getTransport("smtp");
      transport.connect(host, remitente, clave);
      transport.sendMessage(message, message.getAllRecipients());
      transport.close();
    } catch (MessagingException me) {
      me.printStackTrace();
    }


  }

  @Override
  public void contactar(DatosContacto contacto1, DatosContacto contacto2, String msg) {
    notificar(contacto1,
        msg + " Por favor ponganse en contacto con  " + contacto2.getNombre()
            + " mediante su telefono (" + contacto2.getTelefono() + ") o su correo ("
            + contacto2.getMail() + ")");
  }
}
