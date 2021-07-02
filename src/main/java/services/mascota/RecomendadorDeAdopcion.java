package services.mascota;

import java.util.List;
import java.util.stream.Collectors;
import model.preferencia.Preferencia;
import model.publicacion.PublicacionAdoptar;
import model.publicacion.PublicacionDarEnAdopcion;
import repositories.RepoPubDarEnAdopcion;
import repositories.RepoPubParaAdoptar;

/**
 * Servicio de Recomendacion Semanal.
 *
 * @version 1.1
 * @since Entrega III
 * @author Tomás Sánchez
 */
public class RecomendadorDeAdopcion {

  /**
   * Repositorio de Mascotas en Adopción
   */
  private RepoPubDarEnAdopcion repoDarEnAdopcion;

  /**
   * Repo de publicaciones en busqueda de mascotas
   */
  private RepoPubParaAdoptar repoAdoptar;

  public RecomendadorDeAdopcion(RepoPubDarEnAdopcion repoDarEnAdopcion,
      RepoPubParaAdoptar repoAdoptar) {
    this.repoDarEnAdopcion = repoDarEnAdopcion;
    this.repoAdoptar = repoAdoptar;
  }

  public void recomendarAdopcion() {
    repoAdoptar.getInteresados().forEach(publicacion -> {
      publicacion.getInteresado().recomendarAdopcion(matchConMascota(publicacion));
    });
  }

  private List<PublicacionDarEnAdopcion> matchConPosiblesMascota(PublicacionAdoptar interesado) {
    List<PublicacionDarEnAdopcion> match = this.repoDarEnAdopcion.getPublicaciones();
    List<Preferencia> preferencias = interesado.getPreferencias();

    return match.stream()
        .filter(pub -> preferencias.stream().anyMatch(pref -> pref.puedeRecomendarse(pub)))
        .collect(Collectors.toList());
  }

  private PublicacionDarEnAdopcion matchConMascota(PublicacionAdoptar interesado) {
    List<PublicacionDarEnAdopcion> matches = matchConPosiblesMascota(interesado);

    if (matches.isEmpty()) {
      return this.repoDarEnAdopcion.getPublicacionRandom();
    }

    matches.sort((pub1, pub2) -> {

      List<Preferencia> preferencias = interesado.getPreferencias();
      long count1 = contarPreferenciasCumplidas(pub1, preferencias);
      long count2 = contarPreferenciasCumplidas(pub2, preferencias);

      return (int) (count1 - count2);
    });

    return matches.stream().findFirst().get();
  }

  private static long contarPreferenciasCumplidas(PublicacionDarEnAdopcion publicacion,
      List<Preferencia> preferencias) {
    return preferencias.stream().map(p -> p.puedeRecomendarse(publicacion)).filter(self -> self)
        .count();
  }

}
