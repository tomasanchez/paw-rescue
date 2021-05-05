import Exceptions.InvalidPasswordException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PasswordTest {
  @Test
  public void contraseñaEnElTop10000(){
    assertThrows(InvalidPasswordException.class,() -> new RegistroDuenioMascota().password("qwerty"));
    assertThrows(InvalidPasswordException.class,() -> new RegistroDuenioMascota().password("666666"));
    assertThrows(InvalidPasswordException.class,() -> new RegistroDuenioMascota().password("666666"));
    assertThrows(InvalidPasswordException.class,() -> new RegistroDuenioMascota().password("superstar"));
    assertThrows(InvalidPasswordException.class,() -> new RegistroDuenioMascota().password("1222"));
  }
  
}
