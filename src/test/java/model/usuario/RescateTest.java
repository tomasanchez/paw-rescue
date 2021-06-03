package model.usuario;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.mascota.encontrada.MascotaEncontrada;
import model.registro.RegistroRescatista;
import model.usuario.datospersonales.TipoDocumento;
import repositories.AdministracionRescates;
import java.time.LocalDate;

public class RescateTest {

  private AdministracionRescates adminRescates;
  private RegistroRescatista registro;

  @BeforeEach
  void initRescates() {
    adminRescates = new AdministracionRescates();
    registro = nuevoRescate();
  }

  @Test
  void noSeGeneraRescateSinMascota() {
    Assertions.assertThrows(NullPointerException.class, () -> {
      registro.generarRescate();
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
    Assertions.assertTrue(adminRescates.getMascotasEncontradas().contains(rescatista));
  }

  private RegistroRescatista nuevoRescate() {
    RegistroRescatista registroRescatista = new RegistroRescatista(adminRescates);
    registroRescatista.nombre("Lucas").apellido("Gonzalez");
    registroRescatista.contacto(nuevoContato());
    registroRescatista.numeroDocumento(132123412L).tipoDocumento(TipoDocumento.DNI);
    registroRescatista.fechaNacimiento(LocalDate.now());
    return registroRescatista;

  }

  private Contacto nuevoContato() {
    return new Contacto("Tomas", "Dias", null, "tomasDias@gmail.com");
  }

  private void encontrarMascota(final boolean PUEDE) {
    registro.puedeAlbergarMascota(PUEDE);
    registro.mascotaEncontrada(new MascotaEncontrada());
  }


}

