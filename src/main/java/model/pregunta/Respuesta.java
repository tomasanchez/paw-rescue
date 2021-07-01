package model.pregunta;

import exceptions.RespuestaInvalida;


public class Respuesta {
  Pregunta pregunta;
  String respuesta;

  public Respuesta(Pregunta pregunta, String respuesta) {
    this.pregunta = pregunta;
    if (pregunta.getPosiblesRespuestas() != null &&
      !pregunta.getPosiblesRespuestas().contains(respuesta)) {
      throw new RespuestaInvalida(
        "La respuesta no se encuentra entre las posibles respuestas de la pregunta");
    }
    this.respuesta = respuesta;
  }

  public Pregunta getPregunta() {
    return pregunta;
  }

  public String getRespuesta() {
    return respuesta;
  }
}
