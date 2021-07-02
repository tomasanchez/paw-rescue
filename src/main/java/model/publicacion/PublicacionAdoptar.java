package model.publicacion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.preferencia.Preferencia;
import model.pregunta.Pregunta;
import model.pregunta.Respuesta;
import model.usuario.DuenioMascota;

public class PublicacionAdoptar {

  private List<Pregunta> preguntas = new ArrayList<>();
  private Map<Pregunta, Respuesta> respuestas = new HashMap<>();
  private List<Preferencia> preferencias = new ArrayList<>();
  private DuenioMascota interesado;
  private boolean activa;

  public PublicacionAdoptar() {
    setActiva(false);
  }

  public void responderPregunta(Pregunta pregunta, Respuesta respuesta) {
    this.respuestas.put(pregunta, respuesta);
  }

  public void finalizarPublicacion() {
    setActiva(true);
  }

  public void inactivar() {
    setActiva(false);
  }

  public boolean isActiva() {
    return activa;
  }

  private void setActiva(boolean activa) {
    this.activa = activa;
  }

  public Map<Pregunta, Respuesta> getRespuestas() {
    return respuestas;
  }

  public void setRespuestas(Map<Pregunta, Respuesta> respuestas) {
    this.respuestas = respuestas;
  }

  public List<Pregunta> getPreguntas() {
    return preguntas;
  }

  public void addPreguntas(Pregunta pregunta) {
    this.preguntas.add(pregunta);
  }

  public List<Preferencia> getPreferencias() {
    return preferencias;
  }

  public DuenioMascota getInteresado() {
    return interesado;
  }

  public PublicacionAdoptar setInteresado(DuenioMascota interesado) {
    this.interesado = interesado;
    return this;
  }

}


