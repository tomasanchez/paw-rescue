package model.publicacion;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import db.PersistentEntity;
import model.mascota.encontrada.MascotaEncontrada;

@Entity
@Table(name = "Publicaciones_Rescates")
public class PublicacionRescate extends PersistentEntity {

  private boolean activa = false;

  @ManyToOne
  @JoinColumn(name = "asociacion_id")
  private Asociacion asociacion;
  @OneToOne
  @JoinColumn(name = "mascota_id")
  private MascotaEncontrada mascota;

  public void setAsociacion(Asociacion asociacion) {
    this.asociacion = asociacion;
  }

  public PublicacionRescate(MascotaEncontrada mascota, Asociacion asociacion) {
    this.mascota = mascota;
    this.asociacion = asociacion;
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
