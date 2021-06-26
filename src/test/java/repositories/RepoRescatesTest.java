package repositories;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.mascota.encontrada.MascotaEncontrada;
import model.usuario.Rescate;
import model.usuario.datospersonales.DatosPersonales;
import model.usuario.datospersonales.contacto.DatosContacto;

public class RepoRescatesTest {

  private RepoRescates repo;
  private Rescate rescatista;

  @BeforeEach
  void initRepo() {
    repo = new RepoRescates();
    rescatista = spy(new Rescate());
  }

  @Test
  void testAddRescate() {
    when(rescatista.getMascotaEncontrada()).thenReturn(spy(new MascotaEncontrada()));
    repo.addRescate(rescatista);
    Assertions.assertTrue(repo.getRescates().contains(rescatista));
  }

  @Test
  void testGetContactoRescatista() {
    repo.addRescate(rescatista);
    MascotaEncontrada mascota = new MascotaEncontrada();

    DatosPersonales datos = mock(DatosPersonales.class);
    when(datos.getDatosContacto()).thenReturn(mockContacto());

    doReturn(datos).when(rescatista).getDatosPersonales();
    doReturn(mascota).when(rescatista).getMascotaEncontrada();

    Assertions.assertEquals(mockContacto().getMail(),
        repo.contactoRescatista(mascota).getMail());
  }

  @Test
  void testGetMascotasEncontradas() {
    MascotaEncontrada mascota = new MascotaEncontrada();
    doReturn(mascota).when(rescatista).getMascotaEncontrada();
    repo.addRescate(rescatista);
    Assertions.assertTrue(repo.getMascotasEncontradas().contains(mascota));
  }

  @Test
  void testGetRescates() {
    Assertions.assertTrue(repo.getRescates().isEmpty());
  }

  @Test
  void testGetRescatesEnLosUltimosDias() {
    when(rescatista.getMascotaEncontrada()).thenReturn(spy(new MascotaEncontrada()));
    repo.addRescate(rescatista);
    doReturn(LocalDate.now()).when(rescatista).getFechaRescate();
    Assertions.assertTrue(repo.getRescatesEnLosUltimosDias(10).contains(rescatista));
  }

  DatosContacto mockContacto() {
    return new DatosContacto("Tomas", "Dias", "324432", "tomasDias@gmail.com");
  }
}
