package services;

import model.registro.RegistroDuenioMascota;
import model.usuario.DuenioMascota;
import model.usuario.datospersonales.contacto.DatosContacto;
import model.usuario.datospersonales.documento.TipoDocumento;
import repositories.RepoPublicaciones;
import repositories.RepoRescates;
import repositories.RepoUsers;
import services.usuario.contacto.ServicioNotificacion;
import services.usuario.contacto.notificaciones.NotificadorMail;
import services.usuario.contacto.notificaciones.NotificadorSMS;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class NotifiacionesTest {

  private NotificadorMail gmail;
  private NotificadorSMS sms;
  private DuenioMascota duenioMascota;

  @BeforeEach
  void init() {
    duenioMascota = Mockito.spy(nuevoDuenio());
    gmail = Mockito.spy(new NotificadorMail());
    sms = Mockito.spy(new NotificadorSMS());
  }

  @Test
  public void enviarCorreo() {
    duenioMascota.agregarNotificador(gmail);
    doNothing().when(gmail).notificar(any(), any());
    gmail.notificar(nuevoContato(), "Prueba individual");

    verify(gmail, times(1)).notificar(any(), any());
  }

  @Test
  public void enviarSMS() {
    duenioMascota.agregarNotificador(sms);
    doNothing().when(sms).notificar(any(), any());
    sms.notificar(nuevoContato(), "Prueba individual");

    verify(sms, times(1)).notificar(any(), any());
  }

  @Test
  public void enviarNotificaciones() {
    doNothing().when(duenioMascota).notificar(any());
    duenioMascota.notificar("Prueba integral");

    verify(duenioMascota, times(1)).notificar(any());
  }
  
  @Test
  public void enviarNotificacionesDesdeServicio() {
    doNothing().when(duenioMascota).notificar(any());
    ServicioNotificacion.getInstance().notificarDuenioMascotaAdopcion(duenioMascota);
    ServicioNotificacion.getInstance().notificarDuenioMascotaPerdida(duenioMascota);
    
    verify(duenioMascota, times(2)).notificar(any());
  }


  DuenioMascota nuevoDuenio() {
    RegistroDuenioMascota registro = new RegistroDuenioMascota(new RepoUsers());
    registro.nombre("Lucas").apellido("Gonzalez");
    registro.contacto(nuevoContato());
    registro.numeroDocumento(132123412L).tipoDocumento(TipoDocumento.DNI);
    registro.fechaNacimiento(LocalDate.now());
    registro.usuario("LucasGonzales").password("1Contras@Valid$a");
    return registro.mascotaOwner();
  }

  DatosContacto nuevoContato() {
    return new DatosContacto("San", "Candia", "5491141758947", "candia.g.santiago@gmail.com");
  }

}
