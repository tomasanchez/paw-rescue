package model.preferencia;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.mascota.Mascota;
import model.mascota.TipoMascota;
import model.publicacion.PublicacionDarEnAdopcion;

public class PreferenciaTest {

  private Preferencia preferencia;
  private PublicacionDarEnAdopcion publicacion;

  @BeforeEach
  void initPreferencias() {
    publicacion = mock(PublicacionDarEnAdopcion.class);
  }

  @Test
  void seRecomiendaGatoAlPreferirseGato() {
    preferirGato();
    publicarGato();
    Assertions.assertTrue(preferencia.puedeRecomendarse(publicacion));
  }

  @Test
  void noSeRecomiendaGatoAlPreferirsePerro() {
    preferirPerro();
    publicarGato();
    Assertions.assertFalse(preferencia.puedeRecomendarse(publicacion));
  }

  @Test
  void seRecomiendaPerroAlPreferirsePerro() {
    preferirPerro();
    publicarPerro();
    Assertions.assertTrue(preferencia.puedeRecomendarse(publicacion));
  }

  @Test
  void noSeRecomiendaPerroAlPreferirseGato() {
    preferirGato();
    publicarPerro();
    Assertions.assertFalse(preferencia.puedeRecomendarse(publicacion));
  }


  private void preferirGato() {
    preferencia = new PreferenciaDeMascota(TipoMascota.GATO);
  }

  private void publicarGato() {
    when(publicacion.getMascota()).thenReturn(new Mascota().setTipo(TipoMascota.GATO));
  }

  private void preferirPerro() {
    preferencia = new PreferenciaDeMascota(TipoMascota.PERRO);
  }

  private void publicarPerro() {
    when(publicacion.getMascota()).thenReturn(new Mascota().setTipo(TipoMascota.PERRO));
  }


}
