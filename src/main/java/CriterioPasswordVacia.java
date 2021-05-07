import java.util.Objects;

import exeptions.InvalidPasswordException;

public class CriterioPasswordVacia implements CriterioPassword {

  @Override
  public void cumpleCriterio(String usuario, String password) {
    if (Objects.isNull(password)) {
      throw new InvalidPasswordException("No ha ingresado contrania");
    }
  }

}
