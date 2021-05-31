package model.mascota.caracteristica;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Repositorio de Caracteristicas.
 *
 * @since 31.05.2021
 * @version 1.0
 * @author Tomás Sánchez
 */
public class CaracteristicasDisponible {
  private Set<String> caracteristicas = new HashSet<String>();

  /**
   * Agrega una nueva caracteristica.
   * 
   * @param valor la caracteristica
   */
  public void agregar(String valor) {
    caracteristicas.add(Objects.requireNonNull(valor));
  }

  /**
   * Quita una caracteristicas.
   * 
   * @param valor la caracteristica
   */
  public void quitar(String valor) {
    caracteristicas.remove(Objects.requireNonNull(valor));
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
}
