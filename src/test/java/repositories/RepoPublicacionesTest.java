package repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.publicacion.Publicacion;

public class RepoPublicacionesTest {

  private RepoPublicaciones repo;

  @BeforeEach
  void initRepo() {
    repo = new RepoPublicaciones();
  }

  @Test
  void testAgregar() {
    repo.agregar(new Publicacion(null));
    Assertions.assertFalse(repo.getPublicaciones().isEmpty());
  }

  @Test
  void testGetPublicaciones() {
    Assertions.assertNotEquals(null, repo.getPublicaciones());
    Assertions.assertEquals(0, repo.getPublicaciones().size());
  }

  @Test
  void testGetPublicacionesActivas() {
    Publicacion publicacion = new Publicacion(null);
    repo.agregar(publicacion);
    publicacion.activar();
    Assertions.assertTrue(repo.getPublicacionesActivas().contains(publicacion));
    Assertions.assertFalse(repo.getPublicacionesInactivas().contains(publicacion));
  }

  @Test
  void testGetPublicacionesInactivas() {
    Publicacion publicacion = new Publicacion(null);
    repo.agregar(publicacion);
    Assertions.assertFalse(repo.getPublicacionesActivas().contains(publicacion));
    Assertions.assertTrue(repo.getPublicacionesInactivas().contains(publicacion));
  }

}