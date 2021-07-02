package model.pregunta;

import java.util.List;

public class Pregunta {
  String encuesta;
  List<String> posiblesRespuestas = null;

  public Pregunta(String encuesta) {
    this.encuesta = encuesta;
  }

  public String getEncuesta() {
    return encuesta;
  }

  public void agregarRespuestaposible(String respuesta) {
    posiblesRespuestas.add(respuesta);
  }

  public List<String> getPosiblesRespuestas() {
    return posiblesRespuestas;
  }

  public boolean isSobre(String keyword) {
    return getEncuesta().toLowerCase().contains(keyword);
  }
}
