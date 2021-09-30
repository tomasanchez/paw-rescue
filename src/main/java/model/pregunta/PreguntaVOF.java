package model.pregunta;

import java.util.ArrayList;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("VOF")
public class PreguntaVOF extends Pregunta {
  public PreguntaVOF(String encuesta) {
    super(encuesta);
    this.posiblesRespuestas = new ArrayList<>();
    this.posiblesRespuestas.add(new Respuesta(Boolean.toString(true)));
    this.posiblesRespuestas.add(new Respuesta(Boolean.toString(false)));
  }
}
