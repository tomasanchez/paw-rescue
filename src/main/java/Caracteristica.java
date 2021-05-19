import java.util.Objects;

/**
 * Caracteristicas de una Mascota.
 *
 *
 * @since 05.03.2021
 * @version 1.0
 * @author Tomás Sánchez
 */
public class Caracteristica {

  /**
   * La categoría de la caracteristica.
   */
  TipoCaracteristica caracteristica;

  /**
   * Valor semántico de la característica, la característica propiamente dicha.
   */
  Object valor;

  /**
   * Instancia caracteristicas no nulas.
   *
   * @param caracteristica La "categoria" de la caracteristica.
   * @param valor          la característica en sí.
   */
  public Caracteristica(TipoCaracteristica caracteristica, Object valor) {
    this.caracteristica = Objects.requireNonNull(caracteristica);
    this.valor = Objects.requireNonNull(valor);
  }

  public TipoCaracteristica getCaracteristica() {
    return this.caracteristica;
  }

  public Object getValor() {
    return this.valor;
  }
}
enum TipoCaracteristica {
  COLOR1, COLOR2, CASTRADO, MANCHAS, OTRO
}
