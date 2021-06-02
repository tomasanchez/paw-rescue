package model.usuario.datospersonales;

import java.time.LocalDate;
import java.util.Objects;
import model.usuario.Contacto;

/**
 * Abstraccion de datos personales.
 * 
 * @version 1.0
 * @since 05.05.2021
 * @author Tomás Sánchez
 */
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
   * Tipo y Numero de Documento.
   */
  private Documento documento;

  /**
   * La fecha de nacimiento.
   */
  private LocalDate fechaNacimiento;

  /**
   * Datos de un contacto.
   */
  private Contacto contacto;

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
      LocalDate fechaNacimiento, Contacto contacto) {
    this.nombre = Objects.requireNonNull(nombre);
    this.apellido = Objects.requireNonNull(apellido);
    this.documento = Objects.requireNonNull(documento);
    this.fechaNacimiento = Objects.requireNonNull(fechaNacimiento);
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

  public Contacto getContacto() {
    return this.contacto;
  }
}
