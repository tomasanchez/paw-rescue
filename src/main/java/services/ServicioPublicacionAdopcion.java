package services;

import java.util.List;

import model.mascota.Mascota;
import model.pregunta.Respuesta;
import model.publicacion.PublicacionDarEnAdopcion;
import repositories.RepoPubDarEnAdopcion;

public class ServicioPublicacionAdopcion {
  private RepoPubDarEnAdopcion repoDarEnAdopcion = new RepoPubDarEnAdopcion();

  public ServicioPublicacionAdopcion(RepoPubDarEnAdopcion publicaciones) {
    this.repoDarEnAdopcion = publicaciones;
  }

  public void generarPublicacionMascotaEnAdopcion(Mascota mascota, List<Respuesta> respuestas) {
    if (!respuestas.isEmpty())
      repoDarEnAdopcion.createEntity(new PublicacionDarEnAdopcion(mascota, respuestas));
  }
}
