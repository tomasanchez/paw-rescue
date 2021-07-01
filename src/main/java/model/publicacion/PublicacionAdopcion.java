package model.publicacion;

import model.mascota.Mascota;

import java.util.ArrayList;
import java.util.List;

public class PublicacionAdopcion {
  private Mascota mascota;
  private boolean activa = false;
  List<Respuestas> respuestas = new ArrayList<>();
  
  public PublicacionAdopcion(Mascota mascota,List<Respuestas> respuestas) {
    this.mascota = mascota;
    this.respuestas = respuestas;
  }
}
