import java.util.Objects;

/**
 * Usuario común.
 * 
 * @author Kenti
 * @version 2.0
 */
public abstract class Usuario {

  /**
   * Los datos relacionados al nombre, apellido, documento, contacto.
   * 
   * @since 2.0
   */

  /**
   * Usuario de logging.
   * 
   * @since 1.0
   */
  String usuario;

  /**
   * Password del usuario.
   * 
   * @since 1.0
   */
  String password;

  /**
   * Instancia un nuevo usuario.
   *
   * @param usuario nombre de usuario.
   * @param password una password válida.
   */
  public Usuario( String usuario, String password) {
    this.usuario = Objects.requireNonNull(usuario);
    this.password = Objects.requireNonNull(password);
  }

  public String getUsuario() {
    return this.usuario;
  }

  public String getPassword() {
    return this.password;
  }

}
