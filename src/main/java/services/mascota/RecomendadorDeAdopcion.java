package services.mascota;

import repositories.RepoUsers;

public class RecomendadorDeAdopcion {

  /**
   * Repositorio de Mascotas en Adopción
   */
  private Object repoMascotas;

  private RepoUsers repoUsers = RepoUsers.getInstance();

  public RecomendadorDeAdopcion(RepoUsers repoUsers) {
    this.repoUsers = repoUsers;
  }

}
