package model.usuario;

import model.publicacion.Asociacion;

@Deprecated
public class Voluntario extends Usuario {


  Asociacion asociacion;

  public Asociacion getAsociacion() {
    return asociacion;
  }

  public void setAsociacion(Asociacion asociacion) {
    this.asociacion = asociacion;
  }

}
