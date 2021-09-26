package model.refugio;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.Transient;

import db.PersistentEntity;
import model.mascota.TipoMascota;
import model.mascota.caracteristica.Caracteristica;
import model.mascota.caracteristica.TamanioMascota;
import model.mascota.encontrada.Coordenada;
import model.mascota.encontrada.MascotaEncontrada;

@Entity
public class Refugio extends PersistentEntity {

  private String nombre;
  private String direccion;
  @Transient
  private Coordenada coordenada;
  private String telefono;
  @Transient
  private List<TipoMascota> admisiones;
  private int capacidad;
  private int lugares_disponibles;
  private Boolean patio;
  @Transient
  private List<String> caracteristicas;

  public Refugio(String nombre, String direccion, Coordenada coordenada, String telefono, List<TipoMascota> admisiones,
      int capacidad, int lugares_disponibles, Boolean patio, List<String> caracteristicas) {
    this.nombre = nombre;
    this.direccion = direccion;
    this.coordenada = coordenada;
    this.telefono = telefono;
    this.admisiones = admisiones;
    this.capacidad = capacidad;
    this.lugares_disponibles = lugares_disponibles;
    this.patio = patio;
    this.caracteristicas = caracteristicas;

  }

  public String getNombre() {
    return nombre;
  }

  public String getDireccion() {
    return direccion;
  }

  public Coordenada getCoordenada() {
    return coordenada;
  }

  public String getTelefono() {
    return telefono;
  }

  public List<TipoMascota> getAdmisiones() {
    return admisiones;
  }

  public int getCapacidad() {
    return capacidad;
  }

  public int getLugares_disponibles() {
    return lugares_disponibles;
  }

  public Boolean getPatio() {
    return patio;
  }

  public List<String> getCaracteristicas() {
    return caracteristicas;
  }

  public boolean admiteA(MascotaEncontrada mascota) {
    return admiteTipoDeMascota(mascota.getTipoMascota()) && hayLugarDisponible()
        && admiteCaracteristicas(mascota.getCaracteristicas()) && admiteTamanio(mascota.getTamanio());
  }

  public boolean admiteTipoDeMascota(TipoMascota tipo) {
    return getAdmisiones().isEmpty() ? true : getAdmisiones().contains(tipo);
  }

  public boolean hayLugarDisponible() {
    return this.capacidad > this.lugares_disponibles;
  }

  public boolean admiteCaracteristicas(List<Caracteristica> caracteristicas) {
    return getCaracteristicas()
        .containsAll(caracteristicas.stream().map(c -> c.toString()).collect(Collectors.toList()));
  }

  public boolean admiteTamanio(TamanioMascota t) {
    return getPatio() ? true : t.equals(TamanioMascota.PEQUEÑA);
  }

}
