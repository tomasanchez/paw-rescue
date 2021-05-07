import exeptions.InvalidPasswordException;
import java.util.Objects;

public class CriterioPasswordVacia implements CriterioPassword {

  @Override
  public void cumpleCriterio(String usuario, String password) {
    if (Objects.isNull(password)) {
      throw new InvalidPasswordException("No ha ingresado contrania");
    }
  }

}
