package model.preferencia;

import java.util.Objects;
import model.mascota.TipoMascota;
import model.publicacion.PublicacionDarEnAdopcion;

public class PreferenciaDeMascota implements Preferencia {

  private TipoMascota tipo;

  public PreferenciaDeMascota(TipoMascota tipo) {
    this.tipo = tipo;
  }

  @Override
  public boolean puedeRecomendarse(PublicacionDarEnAdopcion publicacion) {

    return !Objects.isNull(tipo) ? tipo.equals(publicacion.getMascota().getTipoMascota()) : true;
  }

}
