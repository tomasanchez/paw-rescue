package model.preferencia;

import model.mascota.TipoMascota;
import model.publicacion.PublicacionDarEnAdopcion;

public class PreferenciaDeMascota implements Preferencia {

  private TipoMascota tipo;

  public PreferenciaDeMascota(TipoMascota tipo) {
    this.tipo = tipo;
  }

  @Override
  public boolean puedeRecomendarse(PublicacionDarEnAdopcion publicacion) {
    return tipo.equals(publicacion.getMascota().getTipoMascota());
  }

}
