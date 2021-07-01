package model.usuario;

import model.registro.RegistroRescate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.mascota.Chapita;
import model.mascota.encontrada.MascotaEncontrada;
import repositories.RepoRescates;
import services.ServicioRescate;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;


public class RescateTest {

  private RepoRescates repoRescates= spy(new RepoRescates());
  private MascotaEncontrada mascota;
  private RegistroRescate registro=  spy(new RegistroRescate());
  private Rescate rescate;
  private ServicioRescate servicioRescate= spy(new ServicioRescate(null, repoRescates, null));

  @BeforeEach
  void initRescates() {
    mascota = new MascotaEncontrada();
    doNothing().when(servicioRescate).identificarMascota(mascota);
  }

  
  @Test
  void noSeGeneraRescateSinMascota() {
    Assertions.assertThrows(NullPointerException.class, () -> {
      repoRescates.addRescate(registro.generarRescate());
    });
  }

  @Test
  void rescatistaAlbergaMascota() {
    encontrarMascota(true);
    Assertions.assertEquals(rescate.getRescatistaAlbergaMascota(), true);
  }

  @Test
  void rescatistaQuedaRegistrado() {
    encontrarMascota(true);
    servicioRescate.registrarRescate(rescate);
    Assertions.assertTrue(repoRescates.getRescates().contains(rescate));
  }

  
  @Test
  void rescatistaEncuentraMascotaConChapita() {
    Chapita chapita = new Chapita(null);
    mascota.setChapita(chapita);
    encontrarMascota(true);
    servicioRescate.registrarRescate(rescate);
    Assertions.assertTrue(repoRescates.getMascotasEncontradas().stream()
        .anyMatch(r -> r.getChapita().equals(chapita)));
  }


  private void encontrarMascota(final boolean PUEDE) {
      registro.albergaMascota(PUEDE);
      registro.mascotaEncontrada(mascota);
      rescate= registro.generarRescate();
  }

}

