package repositories;

import static org.mockito.Mockito.spy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import model.mascota.encontrada.MascotaEncontrada;
import model.usuario.Rescate;
import model.usuario.datospersonales.DatosPersonales;
import model.usuario.datospersonales.contacto.DatosContacto;
import services.ServicioRescate;

public class RepoRescatesTest implements WithGlobalEntityManager {

  private RepoRescates repo;
  private RepoPubRescate publicaciones;
  private RepoUsers users;
  private Rescate rescate;
  private ServicioRescate servicioRescate;
  private MascotaEncontrada mascota;

  @BeforeEach
  void initRepo() {
    entityManager().getTransaction().begin();
    repo = new RepoRescates();
    publicaciones = new RepoPubRescate();
    users = new RepoUsers();
    servicioRescate = spy(new ServicioRescate(publicaciones, repo, users));
    mascota = new MascotaEncontrada();
    rescate = new Rescate();
    rescate.setMascotaEncontrada(mascota);
  }

  @AfterEach
  void endTransaction() {
    entityManager().getTransaction().rollback();
  }

  @Test
  void testAddRescate() {
    DatosPersonales datos = new DatosPersonales();
    datos.setContacto(mockContacto());
    rescate.setDatosRescatista(datos);
    servicioRescate.registrarRescate(rescate);
    entityManager().flush();
    Assertions.assertFalse(repo.getEntitySet().isEmpty());
  }

  @Test
  void testGetContactoRescatista() {
    DatosPersonales datos = new DatosPersonales();
    datos.setContacto(mockContacto());
    rescate.setDatosRescatista(datos);
    servicioRescate.registrarRescate(rescate);
    Assertions.assertEquals(mockContacto().getMail(), repo.contactoRescatista(mascota).getMail());
  }

  @Test
  void testGetMascotasEncontradas() {
    DatosPersonales datos = new DatosPersonales();
    datos.setContacto(mockContacto());
    rescate.setDatosRescatista(datos);
    servicioRescate.registrarRescate(rescate);
    Assertions.assertTrue(repo.getMascotasEncontradas().contains(mascota));
  }

  @Test
  void testGetRescates() {
    Assertions.assertTrue(repo.getRescates().isEmpty());
  }

  @Test
  void testGetRescatesEnLosUltimosDias() {
    repo.addRescate(rescate);
    Assertions.assertTrue(repo.getRescatesEnLosUltimosDias(10).contains(rescate));
  }

  DatosContacto mockContacto() {
    return new DatosContacto("Tomas", "Dias", "324432", "tomasDias@gmail.com");
  }

}
