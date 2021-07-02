package model.preferencia;

import java.util.List;
import model.pregunta.Respuesta;
import model.publicacion.PublicacionDarEnAdopcion;

public class PreferenciaPatio implements Preferencia {

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
