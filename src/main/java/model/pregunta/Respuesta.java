package model.pregunta;

import db.PersistentEntity;
import exceptions.RespuestaInvalida;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Respuestas")
public class Respuesta extends PersistentEntity {
  @ManyToOne
  @JoinColumn(name = "pregunta_id")
  private Pregunta pregunta;
  private String respuesta;

  public Respuesta(Pregunta pregunta, String respuesta) {
    estaDentroDeLasRespuestas(pregunta, respuesta);
    this.pregunta = pregunta;
    this.respuesta = respuesta;
  }

  private Respuesta() {

  }

  public Pregunta getPregunta() {
    return pregunta;
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
