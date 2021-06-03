package model.publicacion;

import model.mascota.encontrada.Coordenada;

public class Asociacion {
  Coordenada coordenada;
  String direccion;
  int idAsociacion;
  
  public Asociacion(Coordenada coordenada,String direccion,int idAsociacion){
    this.idAsociacion = idAsociacion;
    this.coordenada = coordenada;
    this.direccion = direccion;
  }

  public int getIdAsociacion() {
    return idAsociacion;
  }

  public Coordenada getCoordenada() {
    return coordenada;
  }

  public String getDireccion() {
    return direccion;
  }
}
