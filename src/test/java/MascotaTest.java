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
    AdministracionRescates programa = new AdministracionRescates();
    // 3 En los últimos 10 días
    programa.registrarRescate(rescatistaDeMascota(bichitoEncontrado("21111", "311", LocalDate.now())));
    programa.registrarRescate(rescatistaDeMascota(bichitoEncontrado("3223", "5453", LocalDate.now().minusDays(8))));
    programa.registrarRescate(rescatistaDeMascota(bichitoEncontrado("2556", "555", LocalDate.now().minusDays(9))));
    // 3+2 En los ultimos 20 días
    programa.registrarRescate(rescatistaDeMascota(bichitoEncontrado("2555", "23", LocalDate.now().minusDays(15))));
    programa.registrarRescate(rescatistaDeMascota(bichitoEncontrado("21", "5253", LocalDate.now().minusDays(20))));
    Assertions.assertEquals(programa.mascotasEncontradas(n).size(), 3);
  }

  @Test
  public void noSeEncontroNingunaMascotaEnLosUltimosNDias() {
    // Los útlimo N días
    long n = 10;
    AdministracionRescates programa = new AdministracionRescates();
    Assertions.assertEquals(programa.mascotasEncontradas(n).size(), 0);
  }

  static public MascotaEncontrada bichitoEncontrado(String x, String y, LocalDate fecha) {
    return new MascotaEncontrada(null, null, new Coordenada(x, y), fecha,1589742);
  }
  
  static public Rescatista rescatistaDeMascota(MascotaEncontrada mascotaEncontrada){
    return new Rescatista(null , mascotaEncontrada);
  }
  
}
