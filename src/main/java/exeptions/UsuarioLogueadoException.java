package exeptions;

public class UsuarioLogueadoException extends RuntimeException {
  public UsuarioLogueadoException() {
    super("El usuario ya se encuentra logueado");
  }
}
