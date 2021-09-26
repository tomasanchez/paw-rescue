package model.usuario.datospersonales.documento;

import javax.persistence.Embeddable;

/**
 * Tipo de Documentos.
 * 
 * @author Tomás Sánchez.
 * @since 06.02.2021
 * @version 1.0
 */
@Embeddable
public enum TipoDocumento {
  /**
   * Documento Nacional de Identidad.
   */
  DNI,
  /**
   * Cédula de Identidad.
   */
  CI,
  /**
   * Liberta Cívica.
   */
  LC,
  /**
   * Libreta de Enrolamiento.
   */
  LE
}
