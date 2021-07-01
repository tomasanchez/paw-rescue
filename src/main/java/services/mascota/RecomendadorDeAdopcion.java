package services.mascota;

import repositories.RepoInteresados;

public class RecomendadorDeAdopcion {

  /**
   * Repositorio de Mascotas en Adopción
   */
  private Object repoMascotas;

  private RepoInteresados repoInteresados;

  public RecomendadorDeAdopcion(RepoInteresados repoInteresados) {
    this.repoInteresados = repoInteresados;
  }

  public void recomendarAdopcion() {
    System.out.println("Ejecuta una vez");
  }
}
