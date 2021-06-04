package model.publicacion;

import model.mascota.encontrada.Coordenada;
import model.mascota.encontrada.MascotaEncontrada;

public class Asociacion {
  Coordenada coordenada;
  String direccion;
  int idAsociacion;
  
  public Asociacion(Coordenada coordenada,String direccion,int idAsociacion){
    this.idAsociacion = idAsociacion;
    this.coordenada = coordenada;
    this.direccion = direccion;
  }
  public int compararDistanciaConMascota(MascotaEncontrada mascotaEncontrada){
    return this.getCoordenada().distancia() - mascotaEncontrada.getLugar().distancia();
  }
  
  public int compararAsociacionesPorDistancia(Asociacion asociacion,MascotaEncontrada mascotaEncontrada){
    
    if (asociacion.compararDistanciaConMascota(mascotaEncontrada) <
      this.compararDistanciaConMascota(mascotaEncontrada)){
      return asociacion.getCoordenada().distancia();
    }else {
      return this.getCoordenada().distancia();
    }
    
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
