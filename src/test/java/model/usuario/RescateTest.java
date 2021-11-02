package model.usuario;

import static org.mockito.Mockito.spy;

import model.usuario.datospersonales.DatosPersonales;
import model.usuario.datospersonales.contacto.DatosContacto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import model.mascota.Chapita;
import model.mascota.encontrada.MascotaEncontrada;
import model.registro.RegistroRescate;
import repositories.RepoPubRescate;
import repositories.RepoRescates;
import repositories.RepoUsers;
import services.ServicioRescate;

public class RescateTest implements WithGlobalEntityManager {

  private RepoRescates repoRescates;
  private Usuario owner = new Usuario();
  private MascotaEncontrada mascota;
  private RegistroRescate registro = spy(new RegistroRescate());
  private Rescate rescate;
  private ServicioRescate servicioRescate;

  @BeforeEach
  void initRescates() {
    entityManager().getTransaction().begin();
    mascota = new MascotaEncontrada();
    repoRescates = new RepoRescates();
    servicioRescate = new ServicioRescate(new RepoPubRescate(), repoRescates, new RepoUsers());
  }

  @AfterEach
  void endTransaction() {
    entityManager().getTransaction().rollback();
  }

  @Test
  void noSeGeneraRescateSinMascota() {
    Assertions.assertThrows(NullPointerException.class, () -> {
      repoRescates.addRescate(registro.generarRescate());
    });
  }

  @Test
  void rescatistaAlbergaMascota() {
    encontrarMascota(true);
    Assertions.assertEquals(rescate.getRescatistaAlbergaMascota(), true);
  }

  @Test
  void rescatistaQuedaRegistrado() {
    encontrarMascota(true);
    mockContacto();
    servicioRescate.registrarRescate(rescate);
    entityManager().flush();
    Assertions.assertFalse(repoRescates.getEntitySet().isEmpty());
  }

  @Test
  void rescatistaEncuentraMascotaConChapita() {
    Chapita chapita = new Chapita(owner);
    mascota.setChapita(chapita);
    entityManager().persist(owner);
    entityManager().persist(chapita);
    encontrarMascota(true);
    mockContacto();
    servicioRescate.registrarRescate(rescate);
    entityManager().flush();
    Assertions.assertTrue(repoRescates.getMascotasEncontradas().stream()
        .anyMatch(r -> r.getChapita().getId() == chapita.getId()));
  }

  private void encontrarMascota(final boolean PUEDE) {
    registro.albergaMascota(PUEDE);
    registro.mascotaEncontrada(mascota);
    rescate = registro.generarRescate();
  }

  void mockContacto() {
    DatosPersonales datos = new DatosPersonales();
    datos.setContacto(new DatosContacto("Tomas", "Dias", "324432", "tomasDias@gmail.com"));
    rescate.setDatosRescatista(datos);
  }

}
