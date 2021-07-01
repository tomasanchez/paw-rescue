package model.pregunta;


import java.util.ArrayList;

public class PreguntaVOF extends Pregunta {
  public PreguntaVOF(String encuesta) {
    super(encuesta);
    this.posiblesRespuestas = new ArrayList<>();
    this.posiblesRespuestas.add("Verdadero");
    this.posiblesRespuestas.add("Falso");
  }
}
