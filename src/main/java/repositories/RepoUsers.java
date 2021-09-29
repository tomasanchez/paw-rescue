package repositories;

import db.PersistentEntitySet;
import model.mascota.encontrada.MascotaEncontrada;
import model.usuario.DuenioMascota;

public class RepoUsers extends PersistentEntitySet<DuenioMascota> {

  private static RepoUsers instancia;

  public static RepoUsers getInstance() {
    if (instancia == null) {
      instancia = new RepoUsers();
    }
    return instancia;
  }

  public DuenioMascota buscarDuenio(MascotaEncontrada mascota) {
    return getEntity(mascota.getChapita().getDuenio().getId());
  }

  public boolean existeUsuario(DuenioMascota usuario) {
    return getEntitySet().contains(usuario);
  }
}
