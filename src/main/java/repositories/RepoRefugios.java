package repositories;

import java.util.List;
import java.util.stream.Collectors;

import model.mascota.encontrada.MascotaEncontrada;
import model.refugio.Refugio;
import services.ProveedorRefugios;

public class RepoRefugios {
  
  public List<Refugio> obtenerTodosLosRefugios() {
  return ProveedorRefugios.instance().getAllRefugios();
  }
  
  public List<Refugio> obtenerRefugiosQueAdmitenA(MascotaEncontrada mascota){
    return obtenerTodosLosRefugios().stream().filter(refugio -> refugio.admiteA(mascota))
    .collect(Collectors.toList());
  }
  
}
