package exceptions.mascota;

/**
 * Excepciones de Caracteristica.
 */
public class InvalidCaracteristicaException extends RuntimeException {
  /**
   * Lanza una excepción.
   * 
   * @param mensajeError el mensaje de error
   * @throws RuntimeException si la caracterísitca es inválida
   */
  public InvalidCaracteristicaException(String mensajeError) {
    super("La caracterísitca es inválida: " + mensajeError);
  }
}
