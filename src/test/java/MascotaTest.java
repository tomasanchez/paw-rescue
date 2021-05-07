import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Pruebas de Mascotas
 * 
 * @since 05.05.2021
 * @version 1.0
 * @author Tomás Sánchez
 */
public class MascotaTest {

  @Test
  public void mascotasEncontradasEnUltimosNDias() {
    // Los útlimo N días
    long n = 10;
    RescatePatitas programa = new RescatePatitas();
    // 3 En los últimos 10 días
    programa.registrarRescate(bichitoEncontrado("21111", "311", LocalDate.now()));
    programa.registrarRescate(bichitoEncontrado("3223", "5453", LocalDate.now().minusDays(8)));
    programa.registrarRescate(bichitoEncontrado("2556", "555", LocalDate.now().minusDays(9)));
    // 3+2 En los ultimos 20 días
    programa.registrarRescate(bichitoEncontrado("2555", "23", LocalDate.now().minusDays(15)));
    programa.registrarRescate(bichitoEncontrado("21", "5253", LocalDate.now().minusDays(20)));
    Assertions.assertEquals(programa.mascotasEncontradas(n).size(), 3);
  }

  static public MascotaEncontrada bichitoEncontrado(String x, String y, LocalDate fecha) {
    return new MascotaEncontrada(null, null, new Coordenada(x, y), fecha);
  }

  /**
   * Facilita pruebas con una perrita
   * 
   * @param nombre nombre de la perrita
   * @return una nueva perrita
   */
  static public Mascota perritaDePrueba(String nombre) {
    return new Mascota(nombre, "manchitas", TipoMascota.PERRO, 0, Sexo.HEMBRA, "Con manchitas",
        null);
  }

  /**
   * Facilita pruebas con un gatito
   * 
   * @param nombre nombre de la perrita
   * @return un nuevo gatito
   */
  static public Mascota gatitoDePrueba(String nombre) {
    return new Mascota(nombre, "manchitas", TipoMascota.GATO, 0, Sexo.MACHO, "Con manchitas", null);
  }
}
