package model.usuario;

import model.publicacion.Asociacion;


public class Voluntario extends Usuario {

  Asociacion asociacion;

  public Asociacion getAsociacion() {
    return asociacion;
  }

  public void setAsociacion(Asociacion asociacion) {
    this.asociacion = asociacion;
  }

}
