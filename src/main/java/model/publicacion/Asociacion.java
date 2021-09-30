package model.publicacion;

import db.PersistentEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import model.mascota.encontrada.Coordenada;
import model.mascota.encontrada.MascotaEncontrada;
import model.pregunta.Pregunta;
import repositories.RepoPreguntas;

@Entity
@Table(name = "Asociaciones")
public class Asociacion extends PersistentEntity {
  @Embedded
  Coordenada coordenada;
  String direccion;

  @OneToMany
  @JoinColumn(name = "pregunta_id")
  List<Pregunta> preguntas = new ArrayList<>();

  public Asociacion(Coordenada coordenada, String direccion, List<Pregunta> preguntas) {
    this.coordenada = coordenada;
    this.direccion = direccion;
    this.preguntas = preguntas;
  }

  public Double compararDistanciaConMascota(MascotaEncontrada mascotaEncontrada) {
    return this.getCoordenada().distancia(mascotaEncontrada.getLugar());
  }

  public int compararAsociacionesPorDistancia(Asociacion asociacion, MascotaEncontrada mascotaEncontrada) {
    if (asociacion.compararDistanciaConMascota(mascotaEncontrada) < this
        .compararDistanciaConMascota(mascotaEncontrada)) {
      return Integer.parseInt(asociacion.getCoordenada().distancia(mascotaEncontrada.getLugar()).toString());
    } else {
      return Integer.parseInt(this.getCoordenada().distancia(mascotaEncontrada.getLugar()).toString());
    }

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
    todasPreguntas.addAll(repoPreguntas.getEntitySet());
    todasPreguntas.addAll(preguntas);
    return todasPreguntas;
  }

  public void agregarPregunta(Pregunta pregunta) {
    RepoPreguntas.getInstance().createEntity(pregunta);
  }

  public void quitarPregunta(Pregunta pregunta) {
    RepoPreguntas.getInstance().deleteEntity(pregunta);
  }
}
