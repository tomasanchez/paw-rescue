package model.publicacion;

import model.mascota.encontrada.MascotaEncontrada;

public class Publicacion {

  private MascotaEncontrada mascota;
  private boolean activa = false;

  public Publicacion(MascotaEncontrada mascota) {
    this.mascota = mascota;
  }

  public MascotaEncontrada getMascota() {
    return mascota;
  }

  public void setMascota(MascotaEncontrada mascota) {
    this.mascota = mascota;
  }
  
  public boolean isActiva() {
    return this.activa;
  }
/*
  public boolean isNotAprobada() {
    return !isAprobada;
  }

  public void setAprobada(boolean isAprobada) {
    this.isAprobada = isAprobada;
  }
  */

  public Publicacion activar() {
    this.activa = true;
    return this;
  }

  public Publicacion inactivar() {
    this.activa = false;
    return this;
  }
}