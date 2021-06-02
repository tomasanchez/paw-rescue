package repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import model.publicacion.Publicacion;

/**
 * Repositorio de Publicaciones
 * 
 * @version 1.0
 * @since 06.02.2021
 * @author Tomás Sánchez
 */
public class AdministracionPublicaciones {

  /**
   * Listado de publicaciones.
   * 
   * @since Entrega 2
   */
  private List<Publicacion> publicaciones= new ArrayList<Publicacion> ();

  /**
   * Agrega una publicacion al repositorio.
   * 
   * @param publicacion la publicacion a añadir
   * @return el repositorio
   * @since Entrega 2
   */
  public AdministracionPublicaciones agregar(Publicacion publicacion) {
    this.publicaciones.add(Objects.requireNonNull(publicacion));
    return this;
  }

  public List<Publicacion> getPublicaciones() {
    return this.publicaciones;
  }

  public List<Publicacion> getPublicacionesInactivas() {
    return publicaciones.stream().filter(publicacion-> !(publicacion.isActiva())).collect(Collectors.toList());
  }

  public List<Publicacion> getPublicacionesActivas() {
    return publicaciones.stream().filter(publicacion-> publicacion.isActiva()).collect(Collectors.toList());
  }
}
