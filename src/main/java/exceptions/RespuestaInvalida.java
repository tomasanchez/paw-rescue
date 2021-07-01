package exceptions;

public class RespuestaInvalida extends RuntimeException {

  public RespuestaInvalida(String error) {
    super(error);
  }

}
