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

  public void mascotas(List<Mascota> mascotas) {
    this.mascotas = Objects.requireNonNull(mascotas);
  }

  public void usuario(String usuario) {
    this.usuario = Objects.requireNonNull(usuario);
  }

  public void password(String password) {
    if (new ValidadorContrasenia().validarContrasenia(password))
      throw new InvalidPasswordException("La contrasenia esta en el top 10000 de las peores contraseñas");
    this.password = Objects.requireNonNull(password);
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
