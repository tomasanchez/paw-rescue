package model.publicacion;

import java.util.ArrayList;
import java.util.List;

import model.mascota.Mascota;
import model.pregunta.Respuesta;


public class PublicacionAdopcion {
  private Mascota mascota;
  private boolean activa = false;
  List<Respuesta> respuestas = new ArrayList<>();

  public PublicacionAdopcion(Mascota mascota, List<Respuesta> respuestas) {
    this.mascota = mascota;
    this.respuestas = respuestas;
  }
}
