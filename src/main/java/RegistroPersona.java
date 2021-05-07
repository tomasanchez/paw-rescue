import java.time.LocalDate;
import java.util.Objects;

/**
 * Builder de una Persona.
 *
 * @since 05.03.2021
 * @version 2.0
 * @author Kenti
 */
public abstract class RegistroPersona {

  protected String apellido;
  protected String nombre;
  protected LocalDate fechaNacimiento;
  protected Documento.TipoDocumento tipoDocumento;
  protected Long numeroDocumento;
  protected Contacto contacto;

  public RegistroPersona nombre(String nombre) {
    this.nombre = Objects.requireNonNull(nombre);
    return this;
  }

  public RegistroPersona apellido(String apellido) {
    this.apellido = Objects.requireNonNull(nombre);
    return this;
  }

  public RegistroPersona fechaNacimiento(LocalDate fechaNacimiento) {
    this.fechaNacimiento = Objects.requireNonNull(fechaNacimiento);
    return this;
  }

  public RegistroPersona tipoDocumento(Documento.TipoDocumento tipoDocumento) {
    this.tipoDocumento = Objects.requireNonNull(tipoDocumento);
    return this;
  }

  public RegistroPersona numeroDocumento(Long numeroDocumento) {
    this.numeroDocumento = Objects.requireNonNull(numeroDocumento);
    return this;
  }

  public RegistroPersona contacto(Contacto contacto) {
    this.contacto = Objects.requireNonNull(contacto);
    return this;
  }

  /**
   * Registra los datos personales de una Persona.
   *
   * @return Datos personales.
   * @since 2.0
   */
  public Persona datosPeronales() {
    return new Persona(nombre, apellido, registrarDocumento(), fechaNacimiento, contacto);
  }

  /**
   * Registra el documento de una persona.
   *
   * @return un Documento
   * @since 2.0
   */
  private Documento registrarDocumento() {
    return new Documento(tipoDocumento, numeroDocumento.toString());
  }
}
