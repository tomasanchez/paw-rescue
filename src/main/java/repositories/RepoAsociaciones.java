package repositories;

import model.mascota.encontrada.MascotaEncontrada;
import model.publicacion.Asociacion;

import java.util.ArrayList;
import java.util.List;

public class RepoAsociaciones {

  List<Asociacion> asociaciones = new ArrayList<>();
  private static RepoAsociaciones instancia;
  
  public static RepoAsociaciones getInstance() {
    if (instancia == null) {
      instancia = new RepoAsociaciones();
    }
    return instancia;
  }
  
  public Asociacion buscarAsociacion(MascotaEncontrada mascotaEncontrada) {
    ordenarAsociacionesPorDistancia(mascotaEncontrada);
    return asociaciones.get(0);
  }

  public void nuevaAsociacion(Asociacion asociacion){
    asociaciones.add(asociacion);
  }

  public List<Asociacion> getAsociaciones(){
    return asociaciones;
  }


  private void ordenarAsociacionesPorDistancia(MascotaEncontrada mascotaEncontrada) {
    asociaciones.sort((asociacion1, asociacion2) -> asociacion1
      .compararAsociacionesPorDistancia(asociacion2, mascotaEncontrada));
  }
  
  
}
