package repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.mascota.Chapita;
import model.mascota.encontrada.MascotaEncontrada;
import model.usuario.DuenioMascota;

public class RepoUsersTest {

  private RepoUsers repo;
  private DuenioMascota owner;

  @BeforeEach
  void initRepo() {
    repo = new RepoUsers();
    owner = new DuenioMascota();
  }

  @Test
  void testBuscarDuenio() {
    repo.registrarDuenioMascota(owner);
    MascotaEncontrada mascota = new MascotaEncontrada();
    mascota.setChapita(new Chapita(owner));
    Assertions.assertEquals(owner, repo.buscarDuenio(mascota));
  }


  @Test
  void testExisteUsuario() {
    Assertions.assertFalse(repo.existeUsuario(owner));
    repo.registrarDuenioMascota(owner);
    Assertions.assertTrue(repo.existeUsuario(owner));
  }

  @Test
  void testGetUsers() {
    Assertions.assertFalse(repo.getUsers().contains(owner));
  }

  @Test
  void testRegistrarDuenioMascota() {
    repo.registrarDuenioMascota(owner);
    Assertions.assertTrue(repo.getUsers().contains(owner));
  }
}
