package model.publicacion;

import model.mascota.encontrada.MascotaEncontrada;

public class Publicacion {

  private MascotaEncontrada mascota;
  private boolean isAprobada = false;

  public Publicacion(MascotaEncontrada mascota) {
    this.mascota = mascota;
  }

  public MascotaEncontrada getMascota() {
    return mascota;
  }

  public void setMascota(MascotaEncontrada mascota) {
    this.mascota = mascota;
  }

  public boolean isAprobada() {
    return isAprobada;
  }

  public boolean isNotAprobada() {
    return !isAprobada;
  }

  public void setAprobada(boolean isAprobada) {
    this.isAprobada = isAprobada;
  }

  public Publicacion aprobar() {
    this.isAprobada = true;
    return this;
  }

}
