import Exceptions.InvalidPasswordException;

public class CriterioLargoMinimo implements  CriterioPassword{
  private int LONGITUD_MINIMA;
  public CriterioLargoMinimo(int cantMinima){
    this.LONGITUD_MINIMA = cantMinima;
    
  }
  @Override
  public void cumpleCriterio(String usuario, String password) {
    if(password.length() < LONGITUD_MINIMA)
      throw new InvalidPasswordException("La contrasenia tiene menos de " + LONGITUD_MINIMA +" caracteres");
  }
}
