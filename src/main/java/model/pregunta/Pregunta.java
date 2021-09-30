package model.pregunta;

import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import db.PersistentEntity;
import model.pregunta.publicaciones.PregByPub;

@Entity
@Table(name = "Preguntas")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
@DiscriminatorValue("L")
public class Pregunta extends PersistentEntity {

  String encuesta;

  @ElementCollection
  @CollectionTable(name = "RtasByPregunta")
  List<String> posiblesRespuestas = null;

  @OneToMany(mappedBy = "pregunta")
  Set<PregByPub> publicaciones;

  public Pregunta() {
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
