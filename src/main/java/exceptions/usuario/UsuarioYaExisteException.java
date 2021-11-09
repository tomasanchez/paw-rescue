package exceptions.usuario;

public class UsuarioYaExisteException extends RuntimeException {
  public UsuarioYaExisteException() {
    super("El usuario ya existe!");
  }
}
