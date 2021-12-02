package app;

import repositories.RepoPubDarEnAdopcion;
import repositories.RepoPubParaAdoptar;
import services.mascota.RecomendadorDeAdopcion;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Servicio de Recomendacion Semanal.
 *
 * @version 1.0
 * @since Entrega III
 * @author Tomás Sánchez
 */
public class RescatePatitas {

  public static void main(String[] args) {
    ScheduledExecutorService executorService;
    executorService = Executors.newSingleThreadScheduledExecutor();
    executorService.scheduleAtFixedRate(RescatePatitas::tareasProgramadas, 0, 7, TimeUnit.DAYS);
  }

  private static void tareasProgramadas() {
    System.out.println("Iniciando tareas programadas." + new java.util.Date());
    RepoPubParaAdoptar repoInteresados = new RepoPubParaAdoptar();
    RepoPubDarEnAdopcion repoPubDarEnAdopcion = new RepoPubDarEnAdopcion();
    RecomendadorDeAdopcion recomendador =
        new RecomendadorDeAdopcion(repoPubDarEnAdopcion, repoInteresados);
    recomendador.recomendarAdopciones();
  }
}
