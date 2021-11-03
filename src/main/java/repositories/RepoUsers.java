package repositories;

import javax.persistence.NoResultException;
import db.PersistentEntitySet;
import model.mascota.Mascota;
import model.mascota.encontrada.MascotaEncontrada;
import model.usuario.Usuario;

public class RepoUsers extends PersistentEntitySet<Usuario> {

  private static RepoUsers instancia;

  public static RepoUsers getInstance() {
    if (instancia == null) {
      instancia = new RepoUsers();
    }
    return instancia;
  }

  public Usuario buscarDuenio(MascotaEncontrada mascota) {
    return getEntity(mascota.getChapita().getDuenio().getId());
  }

  public Usuario buscarDuenio(Mascota mascota) {
    return getEntity(mascota.getChapita().getDuenio().getId());
  }

  public boolean existeUsuario(Usuario usuario) {
    return getEntitySet().contains(usuario);
  }

  public Usuario getLogin(String user, String password) {

    try {
      return (Usuario) entityManager()
          .createQuery("FROM " + getTableName()
              + " U WHERE U.usuario LIKE :user AND U.password LIKE :password")
          .setParameter("user", user).setParameter("password", password).getSingleResult();
    } catch (NoResultException exception) {
      return null;
    }
  }

}
