package model.mascota.caracteristica;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import exceptions.mascota.InvalidCaracteristicaException;

/**
 * Repositorio de Caracteristicas.
 *
 * @since 31.05.2021
 * @version 1.0
 * @author Tomás Sánchez
 */
public class CaracteristicasDisponible {
  private Set<String> caracteristicas = new HashSet<>();

  /**
   * Agrega una nueva caracteristica.
   * 
   * @param valor la caracteristica
   */
  public void agregar(String valor) {
    validar(valor);
    caracteristicas.add(valor);
  }

  /**
   * Quita una caracteristicas.
   * 
   * @param valor la caracteristica
   */
  public void quitar(String valor) {
    validar(valor);
    caracteristicas.remove(valor);
  }

  /**
   * Permtie saber si está disponible.
   * 
   * @param valor la caracteristica
   * @return si existe
   */
  public boolean isDisponible(String valor) {
    return caracteristicas.contains(valor);
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
  }
}
