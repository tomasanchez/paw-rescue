package model.pregunta;

import java.util.Objects;
import exceptions.RespuestaInvalida;


public class Respuesta {
  private Pregunta pregunta;
  private String respuesta;

  public Respuesta(Pregunta pregunta, String respuesta) {
    estaDentroDeLasRespuestas(pregunta, respuesta);
    this.pregunta = pregunta;
    this.respuesta = respuesta;
  }

  public Pregunta getPregunta() {
    return pregunta;
  }

  public String getRespuesta() {
    return respuesta;
  }

  /**
   * Valida si la respuesta es admitida
   * 
   * @param pregunta la pregunta
   * @param respuesta la respuesta contestada
   * @throws RespuestaInvalida si las posibles respues no son nulas, y la respuesta no esta incluida
   */
  private void estaDentroDeLasRespuestas(Pregunta pregunta, String respuesta) {
    if (!Objects.isNull(pregunta.getPosiblesRespuestas())
        && !pregunta.getPosiblesRespuestas().contains(respuesta)) {
      throw new RespuestaInvalida(
          "La respuesta no se encuentra entre las posibles respuestas de la pregunta");
    }
  }

}
