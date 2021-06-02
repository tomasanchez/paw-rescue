package exeptions;

public class LogueoSinEmailException extends RuntimeException {
  public LogueoSinEmailException() {
    super("El campo email es obligatorio y debe ser una dirección de email válida.");
  }
}
