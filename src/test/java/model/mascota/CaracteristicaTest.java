package model.mascota;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import exceptions.mascota.InvalidCaracteristicaException;
import model.mascota.caracteristica.Caracteristica;
import model.mascota.caracteristica.CaracteristicasDisponible;

/**
 * Prueba de Caracteristicas
 *
 * @since 05.03.2021
 * @version 2.0
 * @author Tomás Sánchez
 */
public class CaracteristicaTest {

  private CaracteristicasDisponible disponibles;

  @BeforeEach
  private void iniciarDisponibles() {
    disponibles = new CaracteristicasDisponible();
    Caracteristica.setDisponibles(disponibles);
  }

  @Test
  public void noVacias() {
    // Sin características vacías
    Assertions.assertThrows(InvalidCaracteristicaException.class, () -> {
      new Caracteristica(null);
    });
  }

  @Test
  public void agregarCaracteristica() {
    disponibles.agregar("Castradx");
    Assertions.assertTrue(disponibles.isDisponible("Castradx"));
  }

  @Test
  public void noSeCreaCaracteristicaNoDisponible() {
    Assertions.assertThrows(InvalidCaracteristicaException.class, () -> {
      new Caracteristica("Castradx");
    });
  }

  @Test
  public void crearCaracteristicaDisponible() {
    disponibles.agregar("Castradx");
    Assertions.assertDoesNotThrow(() -> {
      new Caracteristica("Castradx");
    });
  }

}
