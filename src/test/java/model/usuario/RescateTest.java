package model.usuario;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.mascota.Chapita;
import model.mascota.encontrada.MascotaEncontrada;
import model.registro.RegistroRescatista;
import model.usuario.datospersonales.Contacto;
import model.usuario.datospersonales.documento.TipoDocumento;
import repositories.AdministracionRescates;
import java.time.LocalDate;

public class RescateTest {

  private AdministracionRescates adminRescates;
  private MascotaEncontrada mascota;
  private RegistroRescatista registro;

  @BeforeEach
  void initRescates() {
    adminRescates = new AdministracionRescates();
    mascota = new MascotaEncontrada();
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

  @Test
  void rescatistaEncuentraMascotaConChapita() {
    Chapita chapita = new Chapita(null);
    mascota.setChapita(chapita);
    encontrarMascota(true);
    registro.generarRescate();

    Assertions.assertTrue(adminRescates.getMascotasEncontradas().stream()
        .anyMatch(r -> r.getMascotaEncontrada().getChapita().equals(chapita)));
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
    registro.mascotaEncontrada(mascota);
  }

}

