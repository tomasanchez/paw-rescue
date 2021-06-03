package Rest.Response;

import java.util.List;


public class Hogares {
  public Hogares() {

  }

  private String id;
  private String nombre;
  private Ubicacion ubicacion;
  private String telefono;
  private Admisiones admisiones;
  private int capacidad;
  private int lugares_disponibles;
  private Boolean patio;
  private List<String> caracteristicas;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public Ubicacion getUbicacion() {
    return ubicacion;
  }

  public void setUbicacion(Ubicacion ubicacion) {
    this.ubicacion = ubicacion;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public int getCapacidad() {
    return capacidad;
  }

  public void setCapacidad(int capacidad) {
    this.capacidad = capacidad;
  }

  public int getLugares_disponibles() {
    return lugares_disponibles;
  }

  public void setLugares_disponibles(int lugares_disponibles) {
    this.lugares_disponibles = lugares_disponibles;
  }

  public Boolean getPatio() {
    return patio;
  }

  public void setPatio(Boolean patio) {
    this.patio = patio;
  }

  public List<String> getCaracteristicas() {
    return caracteristicas;
  }

  public void setCaracteristicas(List<String> caracteristicas) {
    this.caracteristicas = caracteristicas;
  }

  public Admisiones getAdmisiones() {
    return admisiones;
  }

  public void setAdmisiones(Admisiones admisiones) {
    this.admisiones = admisiones;
  }
}
