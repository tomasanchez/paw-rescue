package services.mascota;

import java.util.List;
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
    repoAdoptar.getInteresados().forEach(e -> {
      PublicacionDarEnAdopcion match = matchConMascota(e);
      System.out.println("Ejecuto una vez");
    });
  }


  private List<PublicacionDarEnAdopcion> matchConPosiblesMascota(Object object) {
    List<PublicacionDarEnAdopcion> match = this.repoDarEnAdopcion.getPublicaciones();
    return match;
  }

  private PublicacionDarEnAdopcion matchConMascota(Object object) {
    List<PublicacionDarEnAdopcion> match = matchConPosiblesMascota(object);

    if (match.isEmpty()) {
      return this.repoDarEnAdopcion.getPublicacionRandom();
    }

    match.sort((pub1, pub2) -> {

      int count1 = 1, count2 = 0;

      return count1 - count2;
    });

    return match.stream().findFirst().get();
  }

}
