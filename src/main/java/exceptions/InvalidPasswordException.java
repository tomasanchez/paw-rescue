package exceptions;

public class InvalidPasswordException extends RuntimeException {
  public InvalidPasswordException(String mensajeError) {
    super(mensajeError);
  }
}
