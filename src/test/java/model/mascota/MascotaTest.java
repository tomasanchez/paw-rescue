package model.mascota;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.mascota.caracteristica.Caracteristica;
import model.mascota.caracteristica.CaracteristicasDisponible;

/**
 * Pruebas de Mascotas
 * 
 * @since 05.05.2021
 * @version 2.0
 * @author Tomás Sánchez
 */
public class MascotaTest {

  private Mascota mascota;
  private Caracteristica castradx;
  private Caracteristica manchas;

  @BeforeEach
  private void iniciarMascota() {
    mascota = new Mascota();
    this.iniciarCaracteristicas();
  }

  @Test
  void mascotaTieneCaracteristica() {
    mascota.addCaracteristica(castradx);
    Assertions.assertTrue(mascota.poseeCaracteristica(castradx));
  }

  @Test
  void mascotaNoTieneCaracteristica() {
    mascota.addCaracteristica(castradx);
    Assertions.assertFalse(mascota.poseeCaracteristica(manchas));
  }

  private void iniciarCaracteristicas() {

    CaracteristicasDisponible disponibles = new CaracteristicasDisponible();
    disponibles.agregar("Castradx");
    disponibles.agregar("Con manchas");
    Caracteristica.setDisponibles(disponibles);

    castradx = new Caracteristica("Castradx");
    manchas = new Caracteristica("Con manchas");

  }

}
