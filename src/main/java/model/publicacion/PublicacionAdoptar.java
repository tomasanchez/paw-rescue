package model.publicacion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import db.PersistentEntity;
import model.preferencia.Preferencia;
import model.pregunta.Pregunta;
import model.pregunta.Respuesta;
import model.pregunta.publicaciones.PregByAdoptar;
import model.usuario.DuenioMascota;

@Entity
@Table(name = "Publicaciones_De_Adoptar")
public class PublicacionAdoptar extends PersistentEntity {

  private boolean activa;

  @OneToMany(mappedBy = "publicacion")
  private Set<PregByAdoptar> publicaciones;

  @Transient
  private List<Pregunta> preguntas = new ArrayList<>();

  @Transient
  private Map<Pregunta, Respuesta> respuestas = new HashMap<>();

  @Transient
  private List<Preferencia> preferencias = new ArrayList<>();

  @OneToOne
  @JoinColumn(name = "interesado_id")
  private DuenioMascota interesado;

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
