import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Builder de un Usuario.
 * 
 * @author Kenti
 * @version 2.0
 */
public class RegistroUsuario extends RegistroPersona {

  public String usuario;
  public String password;
  List<Mascota> mascotas;


  public RegistroUsuario() {
    this.mascotas = new ArrayList<Mascota>();
  }

  /**
   * Agrega un listado de mascotas al owner de una mascota.
   * 
   * @param mascotas un listado de mascotas.
   * @return el registro a continuar.
   */
  public RegistroUsuario mascotas(List<Mascota> mascotas) {
    this.mascotas = Objects.requireNonNull(mascotas);
    return this;
  }

  /**
   * Añade una mascota a un owner.
   * 
   * @param mascota la mascota a registar.
   * @return el registro a continuar
   */
  public RegistroUsuario mascota(Mascota mascota) {
    this.mascotas.add(Objects.requireNonNull(mascota));
    return this;
  }

  /**
   * Guarda el nombre de usuario.
   * 
   * @param usuario nombre de usuario a crear.
   * @return el registro a continuar.
   */
  public RegistroUsuario usuario(String usuario) {
    this.usuario = Objects.requireNonNull(usuario);
    return this;
  }

  /**
   * Guarda, si verifica la password.
   * 
   * @param password la password a guardar.
   * @return el registro a continuar.
   */
  public RegistroUsuario password(String password) {

    new ValidadorContrasenia().validarPassword(this.usuario, password);

    this.password = password;

    return this;
  }

  /**
   * Realiza el registro de un Usuario.
   * 
   * @return un nuevo Administrador
   */
  public Administrador administrador() {
    return new Administrador(datosPeronales(), usuario, password);
  }

  /**
   * Realiza el registro de un Usuario.
   * 
   * @return un nuevo Mascota Owner.
   */
  public DuenioMascota mascotaOwner() {
    return new DuenioMascota(datosPeronales(), usuario, password, mascotas);
  }
}
