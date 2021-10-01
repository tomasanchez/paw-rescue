package model.publicacion;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import model.usuario.datospersonales.contacto.DatosContacto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import model.mascota.encontrada.MascotaEncontrada;
import model.registro.RegistroRescate;
import model.usuario.DuenioMascota;
import model.usuario.Rescate;
import repositories.RepoAsociaciones;
import repositories.RepoPubRescate;
import repositories.RepoRescates;
import repositories.RepoUsers;
import services.ServicioRescate;
import services.usuario.contacto.ServicioNotificacion;

public class PublicacionRescateTest implements WithGlobalEntityManager {

  private RegistroRescate registroRescate = spy(new RegistroRescate());
  private Rescate rescate;
  private RepoPubRescate repoPublicaciones = spy(new RepoPubRescate());
  private RepoRescates repoRescates = spy(RepoRescates.class);
  private RepoUsers repoUsers = mock(RepoUsers.class);
  private ServicioRescate servicioRescate = spy(new ServicioRescate(repoPublicaciones, repoRescates, repoUsers));
  private MascotaEncontrada mascota = mock(MascotaEncontrada.class);
  private DuenioMascota duenio = mock(DuenioMascota.class);
  private ServicioNotificacion servicioNotificacion = spy(ServicioNotificacion.getInstance());

  @BeforeEach
  void initPublicaciones() {
    entityManager().getTransaction().begin();
    registroRescate.mascotaEncontrada(mascota);
    rescate = registroRescate.generarRescate();
    RepoAsociaciones.getInstance().createEntity(new Asociacion());
  }

  @AfterEach
  void endTransaction() {
    entityManager().getTransaction().rollback();
  }

  void seCreaPublicacionParaMascotaSinChapita() {
    prepararRegistro(false);
    servicioRescate.registrarRescate(rescate);
    Assertions.assertFalse(repoPublicaciones.getEntitySet().isEmpty());
  }

  void noSeCreaPublicacionParaMascotaConChapita() {
    prepararRegistro(true);
    when(repoUsers.buscarDuenio(mascota)).thenReturn(duenio);

    doNothing().when(servicioNotificacion).contactarDuenioMascotaPerdida(duenio, any(DatosContacto.class));
    servicioRescate.registrarRescate(rescate);
    Assertions.assertTrue(repoPublicaciones.getEntitySet().isEmpty());
  }

  void lasPublicacionesRequierenAprobacion() {
    prepararRegistro(true);
    servicioRescate.registrarRescate(rescate);
    Assertions.assertEquals(repoPublicaciones.getPublicacionesInactivas().size(),
        repoPublicaciones.getEntitySet().size());
  }

  void voluntarioPuedeModificarPublicaciones() {
    prepararRegistro(false);
    servicioRescate.registrarRescate(rescate);
    repoPublicaciones.getEntitySet().forEach(PublicacionRescate::activar);
    Assertions.assertTrue(repoPublicaciones.getPublicacionesInactivas().isEmpty());
  }

  private void prepararRegistro(Boolean tieneChapita) {
    when(mascota.tieneChapita()).thenReturn(tieneChapita);
    if (tieneChapita) {
      when(repoUsers.buscarDuenio(mascota)).thenReturn(duenio);
      doNothing().when(servicioNotificacion).contactarDuenioMascotaPerdida(duenio, any(DatosContacto.class));
    }
  }

}
