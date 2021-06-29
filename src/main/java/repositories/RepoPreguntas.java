package repositories;


import exceptions.PublicacionInvalida;
import model.mascota.encontrada.MascotaEncontrada;
import model.publicacion.*;

import java.util.ArrayList;
import java.util.List;


public class RepoPreguntas {
  
  List<Pregunta> preguntas = new ArrayList<>();
  private RepoPublicaciones publicaciones;
  private Asociacion asociacion;

  
  private static RepoPreguntas instancia;

  public static RepoPreguntas getInstance() {
    if (instancia == null) {
      instancia = new RepoPreguntas();
    }
    return instancia;
  }

  boolean preguntasContestadas(){
    return preguntas.stream().allMatch(pregunta1 -> pregunta1.getEstaContestada() == true);
  }

  // Mover metodo 
  void generarPublicacionMascotaEnAdopcion(MascotaEncontrada mascota){//cambiar a Mascota
    if (preguntasContestadas()){
      publicaciones.agregar(new Publicacion(mascota,asociacion));
    }else{
      throw new PublicacionInvalida("Hay preguntas obligatorias sin responder");
    }
  }
}
