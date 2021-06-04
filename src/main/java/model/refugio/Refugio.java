package model.refugio;


import model.mascota.TipoMascota;
import model.mascota.encontrada.Coordenada;

import java.util.List;

public class Refugio {
  private String nombre;
  private String direccion;
  private Coordenada coordenada;
  private String telefono;
  private List<TipoMascota> admisiones;
  private int capacidad;
  private int lugares_disponibles;
  private Boolean patio;
  private List<String> caracteristicas;

  public Refugio(String nombre, String direccion, Coordenada coordenada, String telefono,
                 List<TipoMascota> admisiones, int capacidad, int lugares_disponibles, Boolean patio, List<String> caracteristicas) {
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
}
