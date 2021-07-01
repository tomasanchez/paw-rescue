package repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import model.publicacion.PublicacionRescate;
import model.publicacion.PublicacionDarEnAdopcion;

/**
 * Repositorio de Publicaciones
 *
 * @version 1.0
 * @since 06.02.2021
 * @author Tomás Sánchez
 */
public class RepoPublicaciones {

  /**
   * Listado de publicaciones.
   *
   * @since Entrega 2
   */
  private List<PublicacionRescate> publicaciones = new ArrayList<PublicacionRescate>();
  private List<PublicacionDarEnAdopcion> publicacionAdopciones = new ArrayList<>();


  private static RepoPublicaciones instancia;

  public static RepoPublicaciones getInstance() {
    if (instancia == null) {
      instancia = new RepoPublicaciones();
    }
    return instancia;
  }


  /**
   * Agrega una publicacion al repositorio.
   *
   * @param publicacion la publicacion a añadir
   * @return el repositorio
   * @since Entrega 2
   */
  public RepoPublicaciones agregar(PublicacionRescate publicacion) {
    this.publicaciones.add(Objects.requireNonNull(publicacion));
    return this;
  }

  public RepoPublicaciones agregarPublicacionAdopcion(PublicacionDarEnAdopcion publicacion) {
    this.publicacionAdopciones.add(Objects.requireNonNull(publicacion));
    return this;
  }

  public List<PublicacionRescate> getPublicaciones() {
    return this.publicaciones;
  }

  public List<PublicacionRescate> getPublicacionesInactivas() {
    return publicaciones.stream().filter(publicacion -> !(publicacion.isActiva()))
      .collect(Collectors.toList());
  }

  public List<PublicacionRescate> getPublicacionesActivas() {
    return publicaciones.stream().filter(publicacion -> publicacion.isActiva())
      .collect(Collectors.toList());
  }

}
