package repositories;

import db.PersistentEntitySet;
import model.pregunta.Pregunta;
import model.publicacion.Asociacion;

public class RepoPreguntas extends PersistentEntitySet<Pregunta> {
  // Es un repo con las preguntas generales, que son comunes a todas las
  // asociaciones
  Asociacion asociacion;

  private static RepoPreguntas instancia;

  public static RepoPreguntas getInstance() {
    if (instancia == null) {
      instancia = new RepoPreguntas();
    }
    return instancia;
  }
}
