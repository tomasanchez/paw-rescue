package repositories;

import java.util.ArrayList;
import java.util.List;
import model.pregunta.Pregunta;


public class RepoPreguntas {
  // Es un repo con las preguntas generales, que son comunes a todas las asociaciones
  List<Pregunta> preguntas = new ArrayList<>();

  private static RepoPreguntas instancia;

  public static RepoPreguntas getInstance() {
    if (instancia == null) {
      instancia = new RepoPreguntas();
    }
    return instancia;
  }

  public List<Pregunta> getPreguntas() {
    return preguntas;
  }

  public void agregarPregunta(Pregunta pregunta) {
    preguntas.add(pregunta);
  }

  public void quitarPregunta(Pregunta pregunta) {
    preguntas.remove(pregunta);
  }
}
