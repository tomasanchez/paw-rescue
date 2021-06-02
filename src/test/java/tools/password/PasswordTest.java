package tools.password;

import org.junit.jupiter.api.Test;
import exceptions.InvalidPasswordException;
import model.registro.RegistroDuenioMascota;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PasswordTest {

  @Test
  public void entreLasPeoresPasswords() {
    // Estas password deberrían no aceptarse al estar dentro del TOP 10000
    assertThrows(InvalidPasswordException.class, () -> generarPassword("qwerty"));
    assertThrows(InvalidPasswordException.class, () -> generarPassword("666666"));
    assertThrows(InvalidPasswordException.class, () -> generarPassword("superstar"));
    assertThrows(InvalidPasswordException.class, () -> generarPassword("asd123"));
  }

  @Test
  public void sinPasswordVacias() {
    // Sin sin contraseñas.
    assertThrows(InvalidPasswordException.class, () -> generarPassword(null));
    assertThrows(InvalidPasswordException.class, () -> generarPassword(""));
  }

  @Test
  public void noPasswordCortasStandard() {
    // La longitud de las password debe ser mayor a 8 (standard)
    assertThrows(InvalidPasswordException.class, () -> generarPassword("kenti"));
    assertThrows(InvalidPasswordException.class, () -> generarPassword("quamtum"));
    assertThrows(InvalidPasswordException.class, () -> generarPassword("queti"));
  }

  /**
   * Genera una password válida
   * 
   * @param password la password a generar
   * @return el Registro de usuario a continuar.
   */
  private static RegistroDuenioMascota generarPassword(String password) {
    return new RegistroDuenioMascota().password(password);
  }

}
