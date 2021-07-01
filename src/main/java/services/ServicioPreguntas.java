package services;


import model.mascota.Mascota;
import model.publicacion.PublicacionAdopcion;
import model.publicacion.Respuestas;
import repositories.RepoPublicaciones;

import java.util.List;


public class ServicioPreguntas{
  private RepoPublicaciones publicaciones;

  void generarPublicacionMascotaEnAdopcion(Mascota mascota, List<Respuestas> respuestas) {
      publicaciones.agregarPublicacionAdopcion(new PublicacionAdopcion(mascota,respuestas ));
    }
  }
