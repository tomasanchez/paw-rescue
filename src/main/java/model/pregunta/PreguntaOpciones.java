package model.pregunta;

import java.util.ArrayList;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("O")
public class PreguntaOpciones extends Pregunta {
  public PreguntaOpciones(String encuesta) {
    super(encuesta);
    this.posiblesRespuestas = new ArrayList<>();
  }

  void agregarOpcion(String posibleRespuesta) {
    this.posiblesRespuestas.add(posibleRespuesta);
  }

  void quitarOpcion(String posibleRespuesta) {
    this.posiblesRespuestas.remove(posibleRespuesta);
  }
}
