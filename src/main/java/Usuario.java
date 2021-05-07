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
  Persona datosPeronales;

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
   * @param datosPeronales DNI, Nombre, etc.
   * @param usuario nombre de usuario.
   * @param password una password válida.
   */
  public Usuario(Persona datosPeronales, String usuario, String password) {
    this.datosPeronales = Objects.requireNonNull(datosPeronales);
    this.usuario = Objects.requireNonNull(usuario);
    this.password = Objects.requireNonNull(password);
  }

  public Persona getDatosPeronales() {
    return this.datosPeronales;
  }

  public String getUsuario() {
    return this.usuario;
  }

  public String getPassword() {
    return this.password;
  }

}
