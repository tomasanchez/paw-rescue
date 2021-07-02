package model.publicacion;

import model.mascota.encontrada.Coordenada;
import model.mascota.encontrada.MascotaEncontrada;
import model.pregunta.Pregunta;
import repositories.RepoPreguntas;

import java.util.List;
import java.util.ArrayList;


public class Asociacion {
  Coordenada coordenada;
  String direccion;
  int idAsociacion;
  List<Pregunta> preguntas = new ArrayList<>();

  public Asociacion(Coordenada coordenada, String direccion, int idAsociacion, List<Pregunta> preguntas) {
    this.idAsociacion = idAsociacion;
    this.coordenada = coordenada;
    this.direccion = direccion;
    this.preguntas = preguntas;
  }

  public Double compararDistanciaConMascota(MascotaEncontrada mascotaEncontrada) {
    return this.getCoordenada().distancia(mascotaEncontrada.getLugar());
  }

  public int compararAsociacionesPorDistancia(Asociacion asociacion, MascotaEncontrada mascotaEncontrada) {

    if (asociacion.compararDistanciaConMascota(mascotaEncontrada) <
      this.compararDistanciaConMascota(mascotaEncontrada)) {
      return Integer.parseInt(asociacion.getCoordenada().distancia(mascotaEncontrada.getLugar()).toString());
    } else {
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

  public List<Pregunta> getPreguntas() {
    List<Pregunta> todasPreguntas = new ArrayList<>();
    RepoPreguntas repoPreguntas = RepoPreguntas.getInstance();
    todasPreguntas.addAll(repoPreguntas.getPreguntas());
    todasPreguntas.addAll(preguntas);
    return todasPreguntas;
  }

  public void agregarPregunta(Pregunta pregunta) {
    preguntas.add(pregunta);
  }

  public void quitarPregunta(Pregunta pregunta) {
    preguntas.remove(pregunta);
  }
}
