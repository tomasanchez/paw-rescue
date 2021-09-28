package model.usuario;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import model.publicacion.Asociacion;

@Entity
public class Voluntario extends Usuario {

  @ManyToOne
  @JoinColumn(name = "asociacion_id")
  Asociacion asociacion;

  public Asociacion getAsociacion() {
    return asociacion;
  }

  public void setAsociacion(Asociacion asociacion) {
    this.asociacion = asociacion;
  }

}
