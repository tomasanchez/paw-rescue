package repositories;

import db.PersistentEntitySet;
import model.publicacion.PublicacionDarEnAdopcion;

/**
 * Repositiorio de Publicaciones para dar en Adopción.
 *
 * @version 1.0
 * @since Entrega III
 * @author Tomás Sánchez
 */
public class RepoPubDarEnAdopcion extends PersistentEntitySet<PublicacionDarEnAdopcion> {

  public PublicacionDarEnAdopcion getPublicacionRandom() {
    return getEntitySet().stream().findAny().orElse(null);
  }
}
