package app;

import repositories.RepoUsers;
import services.mascota.RecomendadorDeAdopcion;

public class RescatePatitas {

  public static void main(String[] args) {
    RepoUsers repoUsers = new RepoUsers();
    RecomendadorDeAdopcion recomendador = new RecomendadorDeAdopcion(repoUsers);
    recomendador.recomendarAdopcion();
  }
}
