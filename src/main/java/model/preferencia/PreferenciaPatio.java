package model.preferencia;

import java.util.List;
import javax.persistence.Entity;
import model.pregunta.Respuesta;
import model.publicacion.PublicacionDarEnAdopcion;

@Entity
public class PreferenciaPatio extends Preferencia {

  boolean patioGrande;

  public PreferenciaPatio(boolean patioGrande) {
    this.patioGrande = patioGrande;
  }
  @Override
  public boolean puedeRecomendarse(PublicacionDarEnAdopcion publicacion) {

    List<Respuesta> respuestas = publicacion.getRespuestas();

    if (respuestas.isEmpty()) {
      return true;
    }

    if (!respuestas.stream().anyMatch(p -> p.isSobre("patio"))) {
      return true;
    }

    return respuestas.stream().filter(r -> contienePatio(r)).findFirst().get().isRespuesta("grande")
        && patioGrande;
  }

  private boolean contienePatio(Respuesta respuesta) {
    return respuesta.isSobre("patio");
  }

}
