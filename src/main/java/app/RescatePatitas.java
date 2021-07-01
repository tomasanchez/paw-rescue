package app;

import repositories.RepoInteresados;
import services.mascota.RecomendadorDeAdopcion;

public class RescatePatitas {

  public static void main(String[] args) {
    RepoInteresados repoInteresados = new RepoInteresados();
    RecomendadorDeAdopcion recomendador = new RecomendadorDeAdopcion(repoInteresados);
    recomendador.recomendarAdopcion();
  }
}
