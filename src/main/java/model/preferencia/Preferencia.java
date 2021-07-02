package model.preferencia;

import model.publicacion.PublicacionDarEnAdopcion;

/**
 * Preferencias de Adopcion
 *
 * @version 1.0
 * @since Entrega III
 * @author Tomás Sánchez
 */
public interface Preferencia {
  public boolean puedeRecomendarse(PublicacionDarEnAdopcion publicacion);
}
