package model.mascota.caracteristica;

import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

import exceptions.mascota.InvalidCaracteristicaException;

/**
 * Caracteristicas de una Mascota.
 *
 * @since 05.03.2021
 * @version 2.0
 * @author Tomás Sánchez
 */

@Embeddable
public class Caracteristica{

  @Transient
  private static CaracteristicasDisponible disponibles;

  private String valor;

  /**
   * Instancia una caracteristica.
   * 
   * @param valor la caracteristica.
   */
  public Caracteristica(String valor) {
    validar(valor);
    this.valor = valor;
  }

  public static CaracteristicasDisponible getDisponibles() {
    return disponibles;
  }

  public static void setDisponibles(CaracteristicasDisponible disponibles) {
    Caracteristica.disponibles = disponibles;
  }

  public String getValor() {
    return valor;
  }

  public void setValor(String valor) {
    this.valor = valor;
  }

  /**
   * Valida la caracteristica.
   * 
   * @param valor la caracteristica a validar.
   * @throws InvalidCaracteristicaException si la caracteristica no existe
   */
  private static void validar(String valor) {

    if (Objects.isNull(valor)) {
      throw new InvalidCaracteristicaException("No puede quedar vacía");
    }

    if (!disponibles.isDisponible(valor)) {
      throw new InvalidCaracteristicaException("La característica no existe");
    }

  }

  @Override
  public String toString() {
    return this.valor;
  }

}
