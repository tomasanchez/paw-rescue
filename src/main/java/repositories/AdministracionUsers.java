package repositories;

import java.util.ArrayList;
import java.util.List;
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

  // modificar
  void notificarDuenioMascotaEncontrada(DuenioMascota duenioMascota) {}

  DuenioMascota buscarDueño(MascotaEncontrada mascotaEncontrada) {
    return (DuenioMascota) dueniosMascotas.stream()
        .filter(duenio -> duenio.esDuenio(mascotaEncontrada));
  }

  // modificar
  public void notificarDuenioMascotaPerdida(MascotaEncontrada mascotaEncontrada) {
    notificarDuenioMascotaEncontrada(buscarDueño(mascotaEncontrada));
  }

  public boolean existeUsuario(DuenioMascota usuario) {
    return getDueniosMascotas().contains(usuario);
  }
}