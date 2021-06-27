package model.publicacion;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.mascota.encontrada.MascotaEncontrada;
import model.registro.RegistroRescate;
import model.usuario.DuenioMascota;
import model.usuario.Rescate;
import repositories.RepoPublicaciones;
import repositories.RepoRescates;
import repositories.RepoUsers;
import services.ServicioRescate;

public class PublicacionTest {
  private RegistroRescate registroRescate= spy(new RegistroRescate());
  private Rescate rescate;
  private RepoPublicaciones repoPublicaciones= new RepoPublicaciones();
  private RepoRescates repoRescates= mock(RepoRescates.class);
  private RepoUsers repoUsers= mock(RepoUsers.class);
  private ServicioRescate servicioRescate= spy(new ServicioRescate(repoPublicaciones, repoRescates, repoUsers));
  private MascotaEncontrada mascota= mock(MascotaEncontrada.class);
  private DuenioMascota duenio= mock(DuenioMascota.class);
  
  @BeforeEach
  void initPublicaciones() {
    registroRescate.mascotaEncontrada(mascota);
    rescate= registroRescate.generarRescate();
  }

  @Test
  void seCreaPublicacionParaMascotaSinChapita() {
    prepararRegistro(false);
    servicioRescate.registrarRescate(rescate);
    Assertions.assertFalse(repoPublicaciones.getPublicaciones().isEmpty());
  }

  
  @Test
  void noSeCreaPublicacionParaMascotaConChapita() {
    prepararRegistro(true);
    when(repoUsers.buscarDuenio(mascota)).thenReturn(duenio);
    doNothing().when(servicioRescate).notificarDuenioMascotaPerdida(duenio);
    servicioRescate.registrarRescate(rescate);
    Assertions.assertTrue(repoPublicaciones.getPublicaciones().isEmpty());
  }


  @Test
  void lasPublicacionesRequierenAprobacion() {
    prepararRegistro(true);
    servicioRescate.registrarRescate(rescate);
    Assertions.assertEquals(repoPublicaciones.getPublicacionesInactivas().size(),
        repoPublicaciones.getPublicaciones().size());
  }

  
  @Test
  void voluntarioPuedeModificarPublicaciones() {
    prepararRegistro(false);
    servicioRescate.registrarRescate(rescate);
    repoPublicaciones.getPublicaciones().forEach(Publicacion::activar);
    Assertions.assertTrue(repoPublicaciones.getPublicacionesInactivas().isEmpty());
  }

  private void prepararRegistro(Boolean tieneChapita) {
    when(mascota.tieneChapita()).thenReturn(tieneChapita);
    if(tieneChapita) {
      when(repoUsers.buscarDuenio(mascota)).thenReturn(duenio);
      doNothing().when(servicioRescate).notificarDuenioMascotaPerdida(duenio);
    }
  }

}
