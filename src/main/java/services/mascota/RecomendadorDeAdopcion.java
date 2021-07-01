package services.mascota;

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
  private Object repoMascotas;

  private RepoPubParaAdoptar repoPublicaciones;

  public RecomendadorDeAdopcion(RepoPubParaAdoptar repoPublicaciones) {
    this.repoPublicaciones = repoPublicaciones;
  }

  public void recomendarAdopcion() {
    repoPublicaciones.getInteresados().forEach(e -> System.out.println("Ejecuto una vez"));
  }
}
