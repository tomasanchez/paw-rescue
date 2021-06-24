package model.publicacion;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.mascota.encontrada.MascotaEncontrada;
import model.refugio.Refugio;
import model.registro.RegistroRescatista;
import repositories.RepoPublicaciones;
import repositories.RepoRescates;
import repositories.RepoUsers;

public class PublicacionTest {

  private RepoPublicaciones adminPublicaciones = new RepoPublicaciones();
  private RegistroRescatista registro;
  private MascotaEncontrada mascota;
  private Refugio refugio;

  @BeforeEach
  void initPublicaciones() {
    RepoUsers adminUsers = mock(RepoUsers.class);
    RepoRescates repoRescates = new RepoRescates(adminUsers, adminPublicaciones);
    mascota = mock(MascotaEncontrada.class);
    registro = spy(new RegistroRescatista());
    refugio = mock(Refugio.class);
  }

  @Test
  void seCreaPublicacionParaMascotaSinChapita() {
    prepararRegistro(false);
    registro.generarRescate();
    Assertions.assertFalse(adminPublicaciones.getPublicaciones().isEmpty());
  }

  @Test
  void noSeCreaPublicacionParaMascotaConChapita() {
    prepararRegistro(true);
    registro.generarRescate();
    Assertions.assertTrue(adminPublicaciones.getPublicaciones().isEmpty());
  }

  @Test
  void lasPublicacionesRequierenAprobacion() {
    prepararRegistro(true);
    registro.generarRescate();
    registro.generarRescate();
    Assertions.assertEquals(adminPublicaciones.getPublicacionesInactivas().size(),
        adminPublicaciones.getPublicaciones().size());
  }

  @Test
  void voluntarioPuedeModificarPublicaciones() {
    prepararRegistro(false);
    registro.generarRescate();
    adminPublicaciones.getPublicaciones().forEach(Publicacion::activar);
    Assertions.assertTrue(adminPublicaciones.getPublicacionesInactivas().isEmpty());
  }

  private void prepararRegistro(Boolean tieneChapita) {
    registro.mascotaEncontrada(mascota);
    registro.asignarRefugio(refugio);
    when(mascota.tieneChapita()).thenReturn(tieneChapita);
    doReturn(null).when(registro).datosPersonales();
  }

}
