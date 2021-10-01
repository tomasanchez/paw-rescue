package model.preferencia;

import java.util.Objects;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import model.mascota.TipoMascota;
import model.publicacion.PublicacionDarEnAdopcion;

@Entity
@DiscriminatorValue("Mascota")
public class PreferenciaDeMascota extends Preferencia {

  @Enumerated(EnumType.STRING)
  private TipoMascota tipo;

  public PreferenciaDeMascota(TipoMascota tipo) {
    this.tipo = tipo;
  }

  @Override
  public boolean puedeRecomendarse(PublicacionDarEnAdopcion publicacion) {

    return !Objects.isNull(tipo) ? tipo.equals(publicacion.getMascota().getTipoMascota()) : true;
  }

}
