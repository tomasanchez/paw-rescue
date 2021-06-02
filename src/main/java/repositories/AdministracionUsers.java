package repositories;

import java.util.ArrayList;
import java.util.List;

import model.mascota.Chapita;
import model.mascota.encontrada.MascotaEncontrada;
import model.usuario.DuenioMascota;

public class AdministracionUsers {
  private List<DuenioMascota> dueniosMascotas;

  public AdministracionUsers() {
    this.dueniosMascotas = new ArrayList<>();
  }

  public List<DuenioMascota> getDueniosMascotas() {
    return this.dueniosMascotas;
  }

  public void registrarDuenioMascota(DuenioMascota duenioMascota) {
    dueniosMascotas.add(duenioMascota);
  }
 

  DuenioMascota buscarDuenio(MascotaEncontrada mascota) {
    return dueniosMascotas.stream().filter(duenio -> duenio.equals(mascota.getChapita().getDuenio())).findFirst()
        .orElse(null);
  }


  public boolean existeUsuario(DuenioMascota usuario) {
    return getDueniosMascotas().contains(usuario);
  }
}
