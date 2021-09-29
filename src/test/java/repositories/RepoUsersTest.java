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
import model.usuario.DuenioMascota;

public class RepoUsersTest implements WithGlobalEntityManager {

  private DuenioMascota owner;
  private RepoUsers set;

  @BeforeEach
  void startTransaction() {
    entityManager().getTransaction().begin();
    owner = new DuenioMascota();
    set = new RepoUsers();
  }

  @AfterEach
  void endTransaction() {
    entityManager().getTransaction().rollback();
  }

  @Test
  void sePersisteUnUsuario() {
    set.createEntity(owner);
    assertSame(owner, entityManager().find(DuenioMascota.class, owner.getId()));
  }

  @Test
  void seRecuperaUnUsuario() {
    set.createEntity(owner);
    entityManager().flush();
    assertEquals(owner.getId(), set.getEntity(owner.getId()).getId());
  }

  @Test
  void seRecuperaNULLSiNoExisteUsuario() {
    assertNull(set.getEntity(-1));
  }

  @Test
  void seRecuperaListadoDeUsuarios() {
    set.createEntity(owner);
    set.createEntity(new DuenioMascota());
    entityManager().flush();
    assertEquals(2, set.getEntitySet().size());
  }

  @Test
  void seUpdateaUnUsuario() {
    set.createEntity(owner);
    entityManager().flush();
    owner.setUsuario("@desktop");
    set.updateEntity(owner);
    entityManager().flush();
    assertEquals("@desktop", set.getEntity(owner.getId()).getUsuario());
  }

  @Test
  void seEliminaUnUsuario() {
    set.createEntity(owner);
    entityManager().flush();
    set.deleteEntity(owner);
    entityManager().flush();
    assertEquals(0, set.getEntitySet().size());
  }

  @Test
  void testBuscarDuenio() {
    MascotaEncontrada mascota = new MascotaEncontrada();
    mascota.setChapita(new Chapita(owner));
    set.createEntity(owner);
    entityManager().flush();
    Assertions.assertEquals(owner.getId(), set.buscarDuenio(mascota).getId());
  }
}
