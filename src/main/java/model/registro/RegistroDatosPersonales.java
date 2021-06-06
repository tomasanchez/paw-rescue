package model.registro;

import java.time.LocalDate;
import java.util.Objects;
import model.usuario.datospersonales.DatosPersonales;
import model.usuario.datospersonales.contacto.DatosContacto;
import model.usuario.datospersonales.documento.Documento;
import model.usuario.datospersonales.documento.TipoDocumento;

/**
 * Builder de una Persona.
 *
 * @since 05.03.2021
 * @version 2.0
 * @author Kenti
 */
public abstract class RegistroDatosPersonales {

  protected String apellido;
  protected String nombre;
  protected LocalDate fechaNacimiento;
  protected TipoDocumento tipoDocumento;
  protected Long numeroDocumento;
  protected DatosContacto contacto;

  public RegistroDatosPersonales nombre(String nombre) {
    this.nombre = Objects.requireNonNull(nombre);
    return this;
  }

  public RegistroDatosPersonales apellido(String apellido) {
    this.apellido = Objects.requireNonNull(nombre);
    return this;
  }

  public RegistroDatosPersonales fechaNacimiento(LocalDate fechaNacimiento) {
    this.fechaNacimiento = Objects.requireNonNull(fechaNacimiento);
    return this;
  }

  public RegistroDatosPersonales tipoDocumento(TipoDocumento tipoDocumento) {
    this.tipoDocumento = Objects.requireNonNull(tipoDocumento);
    return this;
  }

  public RegistroDatosPersonales numeroDocumento(Long numeroDocumento) {
    this.numeroDocumento = Objects.requireNonNull(numeroDocumento);
    return this;
  }

  public RegistroDatosPersonales contacto(DatosContacto contacto) {
    this.contacto = Objects.requireNonNull(contacto);
    return this;
  }

  /**
   * Registra los datos personales de una Persona.
   *
   * @return Datos personales.
   * @since 2.0
   */
  public DatosPersonales datosPersonales() {
    return new DatosPersonales(nombre, apellido, registrarDocumento(), fechaNacimiento, contacto);
  }

  /**
   * Registra el documento de una persona.
   *
   * @return un Documento
   * @since 2.0
   */
  private Documento registrarDocumento() {
    return new Documento(tipoDocumento, numeroDocumento);
  }
}


