package model.registro;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import model.mascota.Mascota;
import model.usuario.DuenioMascota;
import repositories.AdministracionUsers;
import tools.password.ValidadorContrasenia;

/**
 * Builder de un Usuario.
 * 
 * @author Kenti
 * @version 2.0
 */
public class RegistroDuenioMascota extends RegistroDatosPersonales {

  public String usuario;
  public String password;
  List<Mascota> mascotas;

  AdministracionUsers adminUsers;

  public RegistroDuenioMascota(AdministracionUsers adminUsers) {
    this.mascotas = new ArrayList<>();
    this.adminUsers = adminUsers;
  }

  /**
   * Añade una mascota a un owner.
   * 
   * //@param mascota la mascota a registar.
   * 
   * @return el registro a continuar
   */
  public RegistroDuenioMascota mascota(List<Mascota> mascotas) {
    this.mascotas = Objects.requireNonNull(mascotas);
    // this.mascotas.add(Objects.requireNonNull(mascota));
    return this;
  }

  /**
   * Guarda el nombre de usuario.
   * 
   * @param usuario nombre de usuario a crear.
   * @return el registro a continuar.
   */
  public RegistroDuenioMascota usuario(String usuario) {
    this.usuario = Objects.requireNonNull(usuario);
    return this;
  }

  /**
   * Guarda, si verifica la password.
   * 
   * @param password la password a guardar.
   * @return el registro a continuar.
   */
  public RegistroDuenioMascota password(String password) {

    new ValidadorContrasenia().validarPassword(this.usuario, password);

    this.password = password;

    return this;
  }

  /**
   * Realiza el registro de un Usuario.
   * 
   * @return un nuevo Mascota Owner.
   */
  public DuenioMascota mascotaOwner() {
    DuenioMascota owner = new DuenioMascota(datosPersonales(), usuario, password, mascotas);
    adminUsers.registrarDuenioMascota(owner);
    return owner;
  }
}
