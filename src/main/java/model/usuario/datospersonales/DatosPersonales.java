package model.usuario.datospersonales;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import model.usuario.datospersonales.contacto.DatosContacto;
import model.usuario.datospersonales.documento.Documento;

/**
 * Abstraccion de datos personales.
 * 
 * @version 1.0
 * @since 05.05.2021
 * @author Tomás Sánchez
 */
@Embeddable
public class DatosPersonales {

  /**
   * El nombre de una persona.
   */
  private String nombre;

  /**
   * El apellido de una persona.
   */
  private String apellido;

  /**
   * La fecha de nacimiento.
   */
  private LocalDate fechaNacimiento;

  /**
   * Tipo y Numero de Documento.
   */
  @Embedded
  private Documento documento;

  /**
   * Datos de un contacto.
   */
  @Embedded
  private DatosContacto contacto;

  public DatosPersonales() {}

  public DatosPersonales(String nombre, String apellido, Documento documento, String mail,
      String tel, LocalDate fechaNacimiento) {
    this(nombre, apellido, documento, fechaNacimiento,
        new DatosContacto(nombre, apellido, tel, mail));
  }

  /**
   * Instancia datos personales.
   * 
   * @param nombre el nombre de la persona
   * @param apellido su apellido
   * @param documento tipo documento y numerodocumento
   * @param fechaNacimiento dia del nacimiento
   * @param contacto datos de un contacto
   */
  public DatosPersonales(String nombre, String apellido, Documento documento,
      LocalDate fechaNacimiento, DatosContacto contacto) {
    this.nombre = Objects.requireNonNull(nombre);
    this.apellido = Objects.requireNonNull(apellido);
    this.documento = Objects.requireNonNull(documento);
    this.fechaNacimiento = Objects.requireNonNull(fechaNacimiento);
    this.contacto = Objects.requireNonNull(contacto);
  }

  public DatosPersonales(DatosContacto contacto) {
    this.nombre = Objects.requireNonNull(contacto.getNombre());
    this.apellido = Objects.requireNonNull(contacto.getApellido());
    this.documento = null;
    this.fechaNacimiento = null;
    this.contacto = Objects.requireNonNull(contacto);
  }

  public String getNombre() {
    return this.nombre;
  }

  public String getApellido() {
    return this.apellido;
  }

  public Documento getDocumento() {
    return this.documento;
  }

  public LocalDate getFechaNacimiento() {
    return this.fechaNacimiento;
  }

  public DatosContacto getDatosContacto() {
    return this.contacto;
  }

  public DatosContacto getContacto() {
    return contacto;
  }

  public void setContacto(DatosContacto contacto) {
    this.contacto = contacto;
  }

}
