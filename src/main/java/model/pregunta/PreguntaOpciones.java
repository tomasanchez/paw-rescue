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

  void agregarOpcion(Respuesta posibleRespuesta) {
    this.posiblesRespuestas.add(posibleRespuesta);
  }

  void agregarOpcion(String posibleRespuesta) {
    this.posiblesRespuestas.add(new Respuesta(posibleRespuesta));
  }

  void quitarOpcion(Respuesta posibleRespuesta) {
    this.posiblesRespuestas.remove(posibleRespuesta);
  }
}
