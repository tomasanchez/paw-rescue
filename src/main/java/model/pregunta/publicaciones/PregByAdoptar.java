package model.pregunta.publicaciones;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import model.pregunta.Pregunta;
import model.pregunta.Respuesta;
import model.publicacion.PublicacionAdoptar;

@Entity
@Table(name = "Preguntas_by_Adoptar")
public class PregByAdoptar {

  @ManyToOne
  @MapsId("pregId")
  @JoinColumn(name = "pregunta_id")
  Pregunta pregunta;

  @ManyToOne
  @MapsId("pubId")
  @JoinColumn(name = "publicacion_id")
  PublicacionAdoptar publicacion;

  @OneToOne
  @JoinColumn(name = "respuesta_id")
  Respuesta respuesta;
}
