package model.publicacion;

import java.util.ArrayList;
import java.util.List;

import model.mascota.Mascota;
import model.pregunta.Respuesta;


public class PublicacionDarEnAdopcion {
  private Mascota mascota;
  private boolean activa = false;
  private List<Respuesta> respuestas = new ArrayList<>();

  public PublicacionDarEnAdopcion(Mascota mascota, List<Respuesta> respuestas) {
    this.mascota = mascota;
    this.respuestas = respuestas;
  }

  public Mascota getMascota() {
    return mascota;
  }

  public void setMascota(Mascota mascota) {
    this.mascota = mascota;
  }

  public boolean isActiva() {
    return activa;
  }

  public void setActiva(boolean activa) {
    this.activa = activa;
  }

  public List<Respuesta> getRespuestas() {
    return respuestas;
  }

  public void setRespuestas(List<Respuesta> respuestas) {
    this.respuestas = respuestas;
  }

}
