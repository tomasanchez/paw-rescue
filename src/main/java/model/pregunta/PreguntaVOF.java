package model.pregunta;


import java.util.ArrayList;

public class PreguntaVOF extends Pregunta {
  public PreguntaVOF(String encuesta) {
    super(encuesta);
    this.posiblesRespuestas = new ArrayList<>();
    this.posiblesRespuestas.add(Boolean.toString(true));
    this.posiblesRespuestas.add(Boolean.toString(false));
  }
}
