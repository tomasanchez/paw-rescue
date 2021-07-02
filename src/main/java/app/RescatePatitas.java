package app;

import repositories.RepoPubDarEnAdopcion;
import repositories.RepoPubParaAdoptar;
import services.mascota.RecomendadorDeAdopcion;

/**
 * Servicio de Recomendacion Semanal.
 *
 * @version 1.0
 * @since Entrega III
 * @author Tomás Sánchez
 */
public class RescatePatitas {

  public static void main(String[] args) {
    RepoPubParaAdoptar repoInteresados = new RepoPubParaAdoptar();
    RepoPubDarEnAdopcion repoPubDarEnAdopcion = new RepoPubDarEnAdopcion();
    RecomendadorDeAdopcion recomendador = new RecomendadorDeAdopcion(repoPubDarEnAdopcion,repoInteresados);
    recomendador.recomendarAdopcion();
  }
}