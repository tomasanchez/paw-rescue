package model.mascota.caracteristica;

import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import db.PersistentEntity;
import exceptions.mascota.InvalidCaracteristicaException;
import model.mascota.Mascota;
import model.mascota.encontrada.MascotaEncontrada;

/**
 * Caracteristicas de una Mascota.
 *
 * @since 05.03.2021
 * @version 2.0
 * @author Tomás Sánchez
 */

@Entity
public class Caracteristica extends PersistentEntity {

  @Transient
  private static CaracteristicasDisponible disponibles;

  private String valor;

  @ManyToMany(mappedBy = "caracteristicas")
  private List<Mascota> mascotas;

  @ManyToMany(mappedBy = "caracteristicas")
  private List<MascotaEncontrada> mascotasEntradas;

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
