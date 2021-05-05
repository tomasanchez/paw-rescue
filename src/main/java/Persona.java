import java.time.LocalDate;

/**
 * Abstraccion de datos personales
 * 
 * @version 1.0
 * @since 05.05.2021
 * @author Tomás Sánchez
 */
public class Persona {

  /**
   * El nombre de una persona.
   */
  String nombre;

  /**
   * El apellido de una persona.
   */
  String apellido;

  /**
   * Tipo y Numero de Documento.
   */
  Documento documento;

  /**
   * La fecha de nacimiento.
   */
  LocalDate fechaNacimiento;

  /**
   * Datos de un contacto.
   */
  Contacto contacto;

  /**
   * 
   * @param nombre          el nombre de la persona
   * @param apellido        su apellido
   * @param documento       tipo y nro de documento
   * @param fechaNacimiento dia del nacimiento
   * @param contacto        datos de un contacto
   */
  public Persona(String nombre, String apellido, Documento documento, LocalDate fechaNacimiento, Contacto contacto) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.documento = documento;
    this.fechaNacimiento = fechaNacimiento;
    this.contacto = contacto;
  }

}
