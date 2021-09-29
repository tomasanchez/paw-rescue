package model.usuario.datospersonales.documento;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Documento de una Persona.
 * 
 * @author Tomás Sánchez.
 * @since 05.02.2021
 * @version 1.0
 */
@Embeddable
public class Documento {

  /**
   * El Tipo de Documento.
   * 
   * @since 1.0
   */
  @Enumerated(EnumType.STRING)
  TipoDocumento tipo;

  /**
   * El número de identificación en sí.
   * 
   * @since 1.0
   */
  @Column(name = "document_id")
  long id;

  public Documento() {
  }

  /**
   * Instancia un nuevo documento.
   * 
   * @param tipo el tipo de documento.
   * @param id   el número de identificación.
   */
  public Documento(TipoDocumento tipo, long id) {
    this.tipo = Objects.requireNonNull(tipo);
    this.id = Objects.requireNonNull(id);
  }

  /**
   * Tipo de un Documento.
   * 
   * @author Tomás Sánchez
   * @since 02.05.2021
   * @version 1.0
   */

  public TipoDocumento getTipo() {
    return this.tipo;
  }

  public long getId() {
    return this.id;
  }
}
