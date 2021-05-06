
import java.util.Objects;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Prueba de Caracteristicas
 * 
 * @since 05.03.2021
 * @version 1.0
 * @author Tomás Sánchez
 */
public class CaracteristicaTest {

  @Test
  public void noVacias() {
    // Sin características vacías
    Assertions.assertThrows(NullPointerException.class, () -> {
      new Caracteristica(null, null);
    });
  }

  @Test
  public void funcionaCastrado() {
    // El valor debe ser el indicado
    boolean valor = true;
    Assertions.assertTrue(Objects.equals(castrado(valor), valor));
  }

  @Test
  public void funcionaColor() {
    // El valor debe ser el indicado
    String color = "Café con Leche";
    Assertions.assertTrue(Objects.equals(color(color), color));
  }

  /**
   * Instancia una caracteristica de Castrado
   * 
   * @param valor si está o no castrado
   * @return El valor de la caracteristica.
   */
  static boolean castrado(boolean valor) {
    return (boolean) new Caracteristica(TipoCaracteristica.CASTRADO, valor).valor;
  }

  /**
   * Instancia una caracteristica de Color Principal.
   * 
   * @param color el color caracteristíco.
   * @return el valor de la característica.
   */
  static String color(String color) {
    return (String) new Caracteristica(TipoCaracteristica.COLOR1, color).valor;
  }

}
