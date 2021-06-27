package model.publicacion;

import model.mascota.encontrada.MascotaEncontrada;

public class Publicacion {

  private MascotaEncontrada mascota;
  private boolean activa = false;
  private Asociacion asociacion;

  public void setAsociacion(Asociacion asociacion) {
    this.asociacion = asociacion;
  }

  public Publicacion(MascotaEncontrada mascota, Asociacion asociacion) {
    this.mascota = mascota;
    this.asociacion=asociacion;
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

  public Publicacion activar() {
    this.activa = true;
    return this;
  }

  public Publicacion inactivar() {
    this.activa = false;
    return this;
  }

  public Asociacion getAsociacion() {
    return this.asociacion;
  }
}
