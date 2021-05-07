import Exceptions.InvalidPasswordException;

import java.util.List;
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

    if (new ValidadorContrasenia().validarContrasenia(password))
      throw new InvalidPasswordException("La contrasenia esta en el top 10000 de las peores contraseñas");

    this.password = Objects.requireNonNull(password);

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
