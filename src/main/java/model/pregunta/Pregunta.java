package model.pregunta;

import java.util.List;
import java.util.Set;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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

  @ManyToMany
  @JoinTable(name = "Preg_Posibles_Respuestas", joinColumns = @JoinColumn(name = "pregunta_id"), inverseJoinColumns = @JoinColumn(name = "rta_id"))
  List<Respuesta> posiblesRespuestas = null;

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

  public void agregarRespuestaposible(Respuesta respuesta) {
    posiblesRespuestas.add(respuesta);
  }

  public List<Respuesta> getPosiblesRespuestas() {
    return posiblesRespuestas;
  }

  public boolean isSobre(String keyword) {
    return getEncuesta().toLowerCase().contains(keyword);
  }
}
