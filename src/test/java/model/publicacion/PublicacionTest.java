package model.publicacion;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.mascota.encontrada.MascotaEncontrada;
import model.registro.RegistroRescatista;
import repositories.AdministracionPublicaciones;
import repositories.AdministracionRescates;
import repositories.AdministracionUsers;

public class PublicacionTest {

  private AdministracionRescates adminRescastes;
  private AdministracionUsers adminUsers;
  private AdministracionPublicaciones adminPublicaciones;
  private RegistroRescatista registro;
  private MascotaEncontrada mascota;

  @BeforeEach
  void initPublicaciones() {
    adminUsers = mock(AdministracionUsers.class);
    adminPublicaciones = new AdministracionPublicaciones();
    adminRescastes = new AdministracionRescates(adminUsers, adminPublicaciones);
    mascota = mock(MascotaEncontrada.class);
    registro = spy(new RegistroRescatista(adminRescastes));
  }

  @Test
  void seCreaPublicacion() {
    prepararRegistro(false);
    registro.generarRescate();
    Assertions.assertFalse(adminPublicaciones.getPublicaciones().isEmpty());
  }

  @Test
  void noSeCreaPublicacionParMascotaConChapita() {
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
    registro.puedeAlbergarMascota(false);
    when(mascota.tieneChapita()).thenReturn(tieneChapita);
    doReturn(null).when(registro).datosPersonales();
  }

}
