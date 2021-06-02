package repositories;

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
  List<Publicacion> publicaciones;

  public AdministracionPublicaciones(List<Publicacion> publicaciones) {
    this.publicaciones = publicaciones;
  }

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

  public List<Publicacion> getPublicacionesNoAprobadas() {
    return publicaciones.stream().filter(Publicacion::isNotAprobada).collect(Collectors.toList());
  }

  public List<Publicacion> getPublicacionesAprobadas() {
    return publicaciones.stream().filter(Publicacion::isAprobada).collect(Collectors.toList());
  }
}
