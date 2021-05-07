import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class AdminTest {

  @Test
  public void seCreaAdmin() {
    assertDoesNotThrow(() -> {
      admindePrueba();
    });
  }

  @Test
  public void adminAgregaCaracteristica() {
    Mascota mascota = MascotaTest.gatitoDePrueba("Gato");
    Administrador admin = admindePrueba();
    Caracteristica castrado = new Caracteristica(Caracteristica.TipoCaracteristica.CASTRADO, true);
    admin.caracterizar(mascota, castrado);
    assertEquals(true, mascota.poseeCaracteristica(castrado.caracteristica));
  }


  public static Administrador admindePrueba() {
    return UsuarioTest.usuarioDePruebas("Admin", "Istrador").administrador();
  }
}
