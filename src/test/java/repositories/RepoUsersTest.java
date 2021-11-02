package repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import model.mascota.Chapita;
import model.mascota.encontrada.MascotaEncontrada;
import model.usuario.Usuario;

public class RepoUsersTest implements WithGlobalEntityManager {

  private Usuario owner;
  private RepoUsers repository;

  @BeforeEach
  void startTransaction() {
    entityManager().getTransaction().begin();
    owner = new Usuario();
    repository = new RepoUsers();
  }

  @AfterEach
  void endTransaction() {
    entityManager().getTransaction().rollback();
  }

  @Test
  void sePersisteUnUsuario() {
    repository.createEntity(owner);
    assertSame(owner, entityManager().find(Usuario.class, owner.getId()));
  }

  @Test
  void seRecuperaUnUsuario() {
    repository.createEntity(owner);
    entityManager().flush();
    assertEquals(owner.getId(), repository.getEntity(owner.getId()).getId());
  }

  @Test
  void seRecuperaNULLSiNoExisteUsuario() {
    assertNull(repository.getEntity(-1));
  }

  @Test
  void seRecuperaListadoDeUsuarios() {
    repository.createEntity(owner);
    repository.createEntity(new Usuario());
    entityManager().flush();
    assertEquals(2, repository.getEntitySet().size());
  }

  @Test
  void seUpdateaUnUsuario() {
    repository.createEntity(owner);
    entityManager().flush();
    owner.setUsuario("@desktop");
    repository.updateEntity(owner);
    entityManager().flush();
    assertEquals("@desktop", repository.getEntity(owner.getId()).getUsuario());
  }

  @Test
  void seEliminaUnUsuario() {
    repository.createEntity(owner);
    entityManager().flush();
    repository.deleteEntity(owner);
    entityManager().flush();
    assertEquals(0, repository.getEntitySet().size());
  }

  @Test
  void testBuscarDuenio() {
    MascotaEncontrada mascota = new MascotaEncontrada();
    mascota.setChapita(new Chapita(owner));
    repository.createEntity(owner);
    entityManager().flush();
    Assertions.assertEquals(owner.getId(), repository.buscarDuenio(mascota).getId());
  }
}
