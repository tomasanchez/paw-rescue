package model.usuario;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.mascota.Chapita;
import model.mascota.encontrada.MascotaEncontrada;
import model.refugio.Refugio;
import model.registro.RegistroRescatista;
import model.usuario.datospersonales.contacto.DatosContacto;
import model.usuario.datospersonales.documento.TipoDocumento;
import repositories.RepoRescates;

import static org.mockito.Mockito.mock;

import java.time.LocalDate;

public class RescateTest {

  private RepoRescates repoRescates;
  private MascotaEncontrada mascota;
  private RegistroRescatista registro;
  private Refugio refugio;

  @BeforeEach
  void initRescates() {
    repoRescates = new RepoRescates();
    mascota = new MascotaEncontrada();
    registro = nuevoRescate();
    refugio = mock(Refugio.class);
  }

  @Test
  void noSeGeneraRescateSinMascota() {
    Assertions.assertThrows(NullPointerException.class, () -> {
      repoRescates.addRescate(registro.generarRescate());
    });
  }

  @Test
  void rescatistaAlbergaMascota() {
    final boolean PUEDE = true;
    encontrarMascota(PUEDE);
    Assertions.assertEquals(registro.generarRescate().albergarMascota, PUEDE);
  }

  @Test
  void rescatistaQuedaRegistrado() {
    encontrarMascota(true);
    Rescatista rescatista = registro.generarRescate();
    Assertions.assertNotNull(rescatista);
  }

  @Test
  void rescatistaEncuentraMascotaConChapita() {
    Chapita chapita = new Chapita(null);
    mascota.setChapita(chapita);
    encontrarMascota(true);
    repoRescates.addRescate(registro.generarRescate());

    Assertions.assertTrue(repoRescates.getMascotasEncontradas().stream()
        .anyMatch(r -> r.getChapita().equals(chapita)));
  }

  private RegistroRescatista nuevoRescate() {
    RegistroRescatista registroRescatista = new RegistroRescatista();
    registroRescatista.nombre("Lucas").apellido("Gonzalez");
    registroRescatista.contacto(nuevoContato());
    registroRescatista.numeroDocumento(132123412L).tipoDocumento(TipoDocumento.DNI);
    registroRescatista.fechaNacimiento(LocalDate.now());
    return registroRescatista;
  }

  private DatosContacto nuevoContato() {
    return new DatosContacto("Tomas", "Dias", null, "tomasDias@gmail.com");
  }

  private void encontrarMascota(final boolean PUEDE) {
    if (PUEDE) {
      registro.albergarMascota("calle falsa 123");
      registro.mascotaEncontrada(mascota);
    } else {
      registro.asignarRefugio(refugio);
    }
  }

}

