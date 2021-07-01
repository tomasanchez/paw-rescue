package model.publicacion;

import model.mascota.encontrada.MascotaEncontrada;

public class PublicacionRescate {

  private MascotaEncontrada mascota;
  private boolean activa = false;
  private Asociacion asociacion;

  public void setAsociacion(Asociacion asociacion) {
    this.asociacion = asociacion;
  }

  public PublicacionRescate(MascotaEncontrada mascota, Asociacion asociacion) {
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

  public PublicacionRescate activar() {
    this.activa = true;
    return this;
  }

  public PublicacionRescate inactivar() {
    this.activa = false;
    return this;
  }

  public Asociacion getAsociacion() {
    return this.asociacion;
  }
}
