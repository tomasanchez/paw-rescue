import Exceptions.InvalidPasswordException;
import java.util.Objects;

/**
 * Builder de un Usuario
 * 
 * @author Kenti
 * @version 2.0
 */
public class RegistroUsuario extends RegistroPersona {

  public String usuario;
  public String password;

  public RegistroUsuario usuario(String usuario) {
    this.usuario = Objects.requireNonNull(usuario);
    return this;
  }

  public RegistroUsuario password(String password) {

    new ValidadorContrasenia().ValidarContrasenia(this.usuario,password);

    this.password = password;

    return this;
  }

  /**
   * Realiza el registro de un Usuario.
   * 
   * @return un nuevo Usuario
   */
  public Usuario administrador() {
    return new Usuario(datosPeronales(), usuario, password);
  }
}
