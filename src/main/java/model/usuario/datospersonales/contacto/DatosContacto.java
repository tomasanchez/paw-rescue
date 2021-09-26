package model.usuario.datospersonales.contacto;

import java.util.Objects;

import javax.persistence.Embeddable;

/**
 * Datos de Contacto.
 * 
 * @author Tomás Sánchez
 * @since 05.02.2021
 * @version 1.0
 */
@Embeddable
public class DatosContacto {

  /**
   * El nombre del contacto.
   * 
   * @since 1.0
   */
  String nombre;

  /**
   * El apellido del contacto.
   * 
   * @since 1.0
   */
  String apellido;

  /**
   * Un número de teléfono.
   * 
   * @since 1.0
   */
  String telefono;

  /**
   * Una dirección de email.
   * 
   * @since 1.0
   */
  String mail;

  /**
   * El contacto propiamente dicho.
   * 
   * @since Entrega 2
   */
  Contacto contacto;

  /**
   * Instancia un contacto.
   * 
   * @param nombre   el nombre del contato.
   * @param apellido el apellido del contacto.
   * @param telefono un número de telefono.
   * @param mail     una direccion de email.
   */
  public DatosContacto(String nombre, String apellido, String telefono, String mail) {
    this.nombre = Objects.requireNonNull(nombre);
    this.apellido = Objects.requireNonNull(apellido);
    this.telefono = telefono;
    this.mail = mail;
  }

  public Contacto getContacto() {
    return contacto;
  }

  public void setContacto(Contacto contacto) {
    this.contacto = contacto;
  }

  public String getNombre() {
    return this.nombre;
  }

  public String getApellido() {
    return this.apellido;
  }

  public String getTelefono() {
    return this.telefono;
  }

  public String getMail() {
    return this.mail;
  }

}
