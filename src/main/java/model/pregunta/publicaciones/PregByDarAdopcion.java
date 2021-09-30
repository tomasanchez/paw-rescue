package model.pregunta.publicaciones;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import model.pregunta.Pregunta;
import model.pregunta.Respuesta;
import model.publicacion.PublicacionDarEnAdopcion;

@Entity
@Table(name = "Preguntas_by_Dar_Adopcion")
public class PregByDarAdopcion {

  @EmbeddedId
  PregPubKey id;

  @ManyToOne
  @MapsId("pregId")
  @JoinColumn(name = "pregunta_id")
  Pregunta pregunta;

  @ManyToOne
  @MapsId("pubId")
  @JoinColumn(name = "publicacion_id")
  PublicacionDarEnAdopcion publicacion;

  @OneToOne
  @JoinColumn(name = "respuesta_id")
  Respuesta respuesta;

  public PregPubKey getId() {
    return id;
  }

  public void setId(PregPubKey id) {
    this.id = id;
  }

  public Pregunta getPregunta() {
    return pregunta;
  }

  public void setPregunta(Pregunta pregunta) {
    this.pregunta = pregunta;
  }

  public PublicacionDarEnAdopcion getPublicacion() {
    return publicacion;
  }

  public void setPublicacion(PublicacionDarEnAdopcion publicacion) {
    this.publicacion = publicacion;
  }

  public Respuesta getRespuesta() {
    return respuesta;
  }

  public void setRespuesta(Respuesta respuesta) {
    this.respuesta = respuesta;
  }

}
