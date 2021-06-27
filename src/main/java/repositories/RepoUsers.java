package repositories;

import java.util.ArrayList;
import java.util.List;
import model.mascota.encontrada.MascotaEncontrada;
import model.usuario.DuenioMascota;

public class RepoUsers {
  private List<DuenioMascota> users = new ArrayList<>();

  private static RepoUsers instancia;

  public static RepoUsers getInstance() {
    if (instancia == null) {
      instancia = new RepoUsers();
    }
    return instancia;
  }

  public List<DuenioMascota> getUsers() {
    return this.users;
  }

  /**
   * Registra un usuario en el repository.
   * 
   * @param duenioMascota el usuario aregistrar;
   */
  public RepoUsers registrarDuenioMascota(DuenioMascota duenioMascota) {
    getUsers().add(duenioMascota);
    return this;
  }

  public DuenioMascota buscarDuenio(MascotaEncontrada mascota) {
    return getUsers().stream().filter(duenio -> duenio.equals(mascota.getChapita().getDuenio()))
        .findFirst().orElse(null);
  }

  public boolean existeUsuario(DuenioMascota usuario) {
    return getUsers().contains(usuario);
  }

}
