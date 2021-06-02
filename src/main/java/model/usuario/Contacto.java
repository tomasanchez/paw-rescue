package model.usuario;

import java.util.Objects;

/**
 * Datos de Contacto.
 * 
 * @author Tomás Sánchez
 * @since 05.02.2021
 * @version 1.0
 */
public class Contacto {

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
   * Instancia un contacto.
   * 
   * @param nombre el nombre del contato.
   * @param apellido el apellido del contacto.
   * @param telefono un número de telefono.
   * @param mail una direccion de email.
   */
  public Contacto(String nombre, String apellido, String telefono, String mail) {
    this.nombre = Objects.requireNonNull(nombre);
    this.apellido = Objects.requireNonNull(apellido);
    this.telefono = telefono;
    this.mail = mail;
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
