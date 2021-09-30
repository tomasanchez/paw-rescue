package model.pregunta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import db.PersistentEntity;
import exceptions.RespuestaInvalida;

@Entity
@Table(name = "Respuestas")
public class Respuesta extends PersistentEntity {

  private String respuesta;

  @ManyToMany(mappedBy = "posiblesRespuestas")
  private List<Pregunta> preguntas;

  public Respuesta() {
  }

  public Respuesta(String respuesta) {
    this.respuesta = respuesta;
  }

  public Respuesta(Pregunta pregunta, String respuesta) {
    estaDentroDeLasRespuestas(pregunta, respuesta);
    this.preguntas = new ArrayList<>();
    this.preguntas.add(pregunta);
    this.respuesta = respuesta;
  }

  public Pregunta getPregunta() {
    return preguntas.get(0);
  }

  public String getRespuesta() {
    return respuesta;
  }

  public boolean isSobre(String keyword) {
    return getPregunta().isSobre(keyword);
  }

  public boolean isRespuesta(String keyword) {
    return getRespuesta().toLowerCase().contains(keyword);
  }

  /**
   * Valida si la respuesta es admitida.
   *
   * @param pregunta  la pregunta
   * @param respuesta la respuesta contestada
   * @throws RespuestaInvalida si las posibles respues no son nulas, y la
   *                           respuesta no esta incluida
   */
  private void estaDentroDeLasRespuestas(Pregunta pregunta, String respuesta) {
    if (!Objects.isNull(pregunta.getPosiblesRespuestas())
        && !pregunta.getPosiblesRespuestas().contains(new Respuesta(respuesta))) {
      throw new RespuestaInvalida("La respuesta no se encuentra entre las posibles respuestas de la pregunta");
    }
  }

}
