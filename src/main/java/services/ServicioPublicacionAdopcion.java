package services;


import model.mascota.Mascota;
import model.pregunta.Respuesta;
import model.publicacion.PublicacionDarEnAdopcion;
import repositories.RepoPublicaciones;

import java.util.List;


public class ServicioPublicacionAdopcion {
  private RepoPublicaciones publicaciones;
  
  public ServicioPublicacionAdopcion(RepoPublicaciones publicaciones){
    this.publicaciones = publicaciones;
  }

  public void generarPublicacionMascotaEnAdopcion(Mascota mascota, List<Respuesta> respuestas) {
    publicaciones.agregarPublicacionAdopcion(new PublicacionDarEnAdopcion(mascota, respuestas));
  }
}
