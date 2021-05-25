package Password;
import exeptions.InvalidPasswordException;

public class CriterioLargoMinimo implements CriterioPassword {
  private int longitudMinima;

  public CriterioLargoMinimo(int cantMinima) {
    this.longitudMinima = cantMinima;

  }

  @Override
  public void cumpleCriterio(String usuario, String password) {
    if (password.length() < longitudMinima) {
      throw new InvalidPasswordException(
          "La contrasenia tiene menos de " + longitudMinima + " caracteres");
    }
  }
}
