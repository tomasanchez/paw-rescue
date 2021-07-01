package app;

import repositories.RepoPubParaAdoptar;
import services.mascota.RecomendadorDeAdopcion;

public class RescatePatitas {

  public static void main(String[] args) {
    RepoPubParaAdoptar repoInteresados = new RepoPubParaAdoptar();
    RecomendadorDeAdopcion recomendador = new RecomendadorDeAdopcion(repoInteresados);
    recomendador.recomendarAdopcion();
  }
}
