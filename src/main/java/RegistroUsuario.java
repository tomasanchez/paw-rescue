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

  private String usuario;
  private String password;
  List<Mascota> mascotas;

  public RegistroUsuario mascotas(List<Mascota> mascotas) {
    this.mascotas = Objects.requireNonNull(mascotas);

    return this;
  }

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
  public Usuario duenioMascota() {
    return new Usuario(datosPeronales(), usuario, password, mascotas);
  }
}
