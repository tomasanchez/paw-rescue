import java.util.List;
import java.util.Objects;

/**
 * Usuario común.
 * 
 * @author Kenti
 * @version 2.0
 */
public class Usuario {

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
   * Password del usuario
   * 
   * @since 1.0
   */
  String password;

  /**
   * Mascotas registadas
   * 
   * @since 1.0
   */
  List<Mascota> mascotas;

  public Usuario(Persona datosPeronales, String usuario, String password, List<Mascota> mascotas) {
    this.datosPeronales = Objects.requireNonNull(datosPeronales);
    this.usuario = Objects.requireNonNull(usuario);
    this.password = Objects.requireNonNull(password);
    this.mascotas = Objects.requireNonNull(mascotas);
  }

}
