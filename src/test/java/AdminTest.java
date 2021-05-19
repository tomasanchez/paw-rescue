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
  
  public static Administrador admindePrueba() {
    return UsuarioTest.usuarioDePruebas("Admin", "Istrador").administrador();
  }
}
