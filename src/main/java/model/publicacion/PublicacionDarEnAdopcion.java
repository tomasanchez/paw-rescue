package model.publicacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import db.PersistentEntity;
import model.mascota.Mascota;
import model.pregunta.Respuesta;
import model.pregunta.publicaciones.PregByDarAdopcion;

@Entity
@Table(name = "Publicaciones_Dar_Adopcion")
public class PublicacionDarEnAdopcion extends PersistentEntity {

  private boolean activa = false;

  @OneToOne
  @JoinColumn(name = "mascota_id")
  private Mascota mascota;

  @OneToMany(mappedBy = "publicacion")
  private Set<PregByDarAdopcion> preguntas;

  @Transient
  private List<Respuesta> respuestas = new ArrayList<>();

  public PublicacionDarEnAdopcion(Mascota mascota, List<Respuesta> respuestas) {
    this.mascota = mascota;
    this.respuestas = respuestas;
  }

  public Mascota getMascota() {
    return mascota;
  }

  public void setMascota(Mascota mascota) {
    this.mascota = mascota;
  }

  public boolean isActiva() {
    return activa;
  }

  public void setActiva(boolean activa) {
    this.activa = activa;
  }

  public List<Respuesta> getRespuestas() {
    return respuestas;
  }

  public void setRespuestas(List<Respuesta> respuestas) {
    this.respuestas = respuestas;
  }

}
