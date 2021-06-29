package exceptions;

public class PublicacionInvalida extends RuntimeException {
  
  public PublicacionInvalida(String error) {
    super(error);
  }
}
