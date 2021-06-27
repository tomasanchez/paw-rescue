package repositories;

import static org.mockito.Mockito.doNothing;
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
import services.ServicioRescate;

public class RepoRescatesTest {

  private RepoRescates repo= spy(RepoRescates.class);
  private Rescate rescate= spy(new Rescate());
  private ServicioRescate servicioRescate= spy(new ServicioRescate(null, repo, null));
  private MascotaEncontrada mascota= mock(MascotaEncontrada.class);
  
  @BeforeEach
  void initRepo() {
    when(rescate.getMascotaEncontrada()).thenReturn(mascota);
    doNothing().when(servicioRescate).identificarMascota(mascota);
  }

  @Test
  void testAddRescate() {
    servicioRescate.registrarRescate(rescate);
    Assertions.assertTrue(repo.getRescates().contains(rescate));
  }


  @Test
  void testGetContactoRescatista() {
    DatosPersonales datos = mock(DatosPersonales.class);
    when(datos.getDatosContacto()).thenReturn(mockContacto());
    doReturn(datos).when(rescate).getDatosRescatista();
    servicioRescate.registrarRescate(rescate);
    Assertions.assertEquals(mockContacto().getMail(),
        repo.contactoRescatista(mascota).getMail());
  }

  
  @Test
  void testGetMascotasEncontradas() {
    servicioRescate.registrarRescate(rescate);
    Assertions.assertTrue(repo.getMascotasEncontradas().contains(mascota));
  }

  @Test
  void testGetRescates() {
    Assertions.assertTrue(repo.getRescates().isEmpty());
  }

  @Test
  void testGetRescatesEnLosUltimosDias() {
    when(rescate.getMascotaEncontrada()).thenReturn(spy(new MascotaEncontrada()));
    repo.addRescate(rescate);
    doReturn(LocalDate.now()).when(rescate).getFechaRescate();
    Assertions.assertTrue(repo.getRescatesEnLosUltimosDias(10).contains(rescate));
  }
  
  DatosContacto mockContacto() {
    return new DatosContacto("Tomas", "Dias", "324432", "tomasDias@gmail.com");
  }
 
}
