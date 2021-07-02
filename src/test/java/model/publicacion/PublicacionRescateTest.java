package model.publicacion;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import model.registro.RegistroRescate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.mascota.encontrada.MascotaEncontrada;
import model.usuario.DuenioMascota;
import model.usuario.Rescate;
import repositories.RepoAsociaciones;

import repositories.RepoPublicaciones;
import repositories.RepoRescates;
import repositories.RepoUsers;
import services.ServicioRescate;
import services.usuario.contacto.ServicioNotificacion;

public class PublicacionRescateTest {
  
  private RegistroRescate registroRescate= spy(new RegistroRescate());
  private Rescate rescate;
  private RepoPublicaciones repoPublicaciones = spy(new RepoPublicaciones());
  private RepoRescates repoRescates= spy(RepoRescates.class);
  private RepoUsers repoUsers= mock(RepoUsers.class);
  private ServicioRescate servicioRescate= spy(new ServicioRescate(repoPublicaciones, repoRescates, repoUsers));
  private MascotaEncontrada mascota= mock(MascotaEncontrada.class);
  private DuenioMascota duenio= mock(DuenioMascota.class);
  private ServicioNotificacion servicioNotificacion = spy(ServicioNotificacion.getInstance());

  @BeforeEach
  void initPublicaciones() {
    registroRescate.mascotaEncontrada(mascota);
    rescate= registroRescate.generarRescate();
    RepoAsociaciones.getInstance().nuevaAsociacion(mock(Asociacion.class));
  }

  @Test
  void seCreaPublicacionParaMascotaSinChapita() {
    prepararRegistro(false);
    servicioRescate.registrarRescate(rescate);
    Assertions.assertFalse(repoPublicaciones.getPublicacionesRescates().isEmpty());
  }
  
  @Test
  void noSeCreaPublicacionParaMascotaConChapita() {
    prepararRegistro(true);
    when(repoUsers.buscarDuenio(mascota)).thenReturn(duenio);
    
    doNothing().when(servicioNotificacion).notificarDuenioMascotaPerdida(duenio);
    servicioRescate.registrarRescate(rescate);
    Assertions.assertTrue(repoPublicaciones.getPublicacionesRescates().isEmpty());
  }

  @Test
  void lasPublicacionesRequierenAprobacion() {
    prepararRegistro(true);
    servicioRescate.registrarRescate(rescate);
    Assertions.assertEquals(repoPublicaciones.getPublicacionesInactivas().size(),
        repoPublicaciones.getPublicacionesRescates().size());
  }
  
  @Test
  void voluntarioPuedeModificarPublicaciones() {
    prepararRegistro(false);
    servicioRescate.registrarRescate(rescate);
    repoPublicaciones.getPublicacionesRescates().forEach(PublicacionRescate::activar);
    Assertions.assertTrue(repoPublicaciones.getPublicacionesInactivas().isEmpty());
  }

  private void prepararRegistro(Boolean tieneChapita) {
    when(mascota.tieneChapita()).thenReturn(tieneChapita);
    if(tieneChapita) {
      when(repoUsers.buscarDuenio(mascota)).thenReturn(duenio);
      doNothing().when(servicioNotificacion).notificarDuenioMascotaPerdida(duenio);
    }
  }

  
}