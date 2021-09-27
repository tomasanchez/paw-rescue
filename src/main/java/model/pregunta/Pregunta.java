package model.pregunta;

import db.PersistentEntity;
import java.util.List;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "Preguntas")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
@DiscriminatorValue("L")
public class Pregunta extends PersistentEntity {
  String encuesta;
  @ElementCollection
  List<String> posiblesRespuestas = null;

  private Pregunta() {
  }

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
