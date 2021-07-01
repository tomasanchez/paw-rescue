package model.publicacion;

import model.mascota.encontrada.Coordenada;
import model.mascota.encontrada.MascotaEncontrada;

import java.util.List;

public class Asociacion {
  Coordenada coordenada;
  String direccion;
  int idAsociacion;

  public Asociacion(Coordenada coordenada,String direccion,int idAsociacion){
    this.idAsociacion = idAsociacion;
    this.coordenada = coordenada;
    this.direccion = direccion;
  }

  public Double compararDistanciaConMascota(MascotaEncontrada mascotaEncontrada){
    return this.getCoordenada().distancia(mascotaEncontrada.getLugar());
  }

  public int compararAsociacionesPorDistancia(Asociacion asociacion, MascotaEncontrada mascotaEncontrada){

    if (asociacion.compararDistanciaConMascota(mascotaEncontrada) <
      this.compararDistanciaConMascota(mascotaEncontrada)){
      return Integer.parseInt(asociacion.getCoordenada().distancia(mascotaEncontrada.getLugar()).toString());
    }else {
      return Integer.parseInt(this.getCoordenada().distancia(mascotaEncontrada.getLugar()).toString());
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
