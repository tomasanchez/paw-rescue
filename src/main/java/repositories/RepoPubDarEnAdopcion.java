package repositories;

import java.util.ArrayList;
import java.util.List;
import model.publicacion.PublicacionDarEnAdopcion;

/**
 * Repositiorio de Publicaciones para dar en Adopción.
 *
 * @version 1.0
 * @since Entrega III
 * @author Tomás Sánchez
 */
public class RepoPubDarEnAdopcion {
  private List<PublicacionDarEnAdopcion> publicaciones = new ArrayList<>();

  public List<PublicacionDarEnAdopcion> getPublicaciones() {
    return publicaciones;
  }

  public void setPublicaciones(List<PublicacionDarEnAdopcion> publicaciones) {
    this.publicaciones = publicaciones;
  }


  public PublicacionDarEnAdopcion getPublicacionRandom() {
    return getPublicaciones().stream().findAny().orElse(null);
  }

  public void addPublicacion(PublicacionDarEnAdopcion pub) {
    getPublicaciones().add(pub);
  }
}
