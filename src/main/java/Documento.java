import java.util.Objects;

/**
 * Documento de una Persona
 * 
 * @author Tomás Sánchez
 * @since 05.02.2021
 * @version 1.0
 */
public class Documento {

  /**
   * El Tipo de Documento
   * 
   * @since 1.0
   */
  TipoDocumento tipo;

  /**
   * El número de identificación en sí
   * 
   * @since 1.0
   */
  String id;

  /**
   * Instancia un nuevo documento
   * 
   * @param tipo el tipo de documento
   * @param id   el número de identificación
   */
  public Documento(Documento.TipoDocumento tipo, String id) {
    this.tipo = Objects.requireNonNull(tipo);
    this.id = Objects.requireNonNull(id);
  }

  /**
   * Tipo de un Documento
   * 
   * @author Tomás Sánchez
   * @since 02.05.2021
   * @version 1.0
   */
  static enum TipoDocumento {
    DNI, CI, LC, LE
  }
  
  public TipoDocumento getTipo(){
    return this.tipo;
  }
  
  public String getId(){
    return this.id;
  }
}
