package model.usuario;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.mascota.Mascota;
import model.mascota.encontrada.MascotaEncontrada;
import model.registro.RegistroDuenioMascota;
import model.usuario.datospersonales.TipoDocumento;
import repositories.AdministracionRescates;
import repositories.AdministracionUsers;


public class DuenioMascotaTest {


  private DuenioMascota duenio;
  private AdministracionUsers adminUsers;

  @BeforeEach
  void iniciarDuenio() {
    adminUsers = new AdministracionUsers();
    duenio = nuevoDuenio();
  }

  @Test
  void tieneMascotas() {
    duenio.registrarMascota(new Mascota());
    Assertions.assertEquals(duenio.getCantidadMascotas(), 1);
  }

  @Test
  void puedeNoTenerMascotas() {
    Assertions.assertEquals(duenio.getCantidadMascotas(), 0);
  }

  @Test
  void duenioEstaRegistrado() {
    Assertions.assertTrue(adminUsers.existeUsuario(duenio));
  }

  @Test
  void duenioPoneChapitaAMascota() {
    Mascota mascota = new Mascota();
    duenio.registrarMascota(mascota);
    Assertions.assertEquals(duenio, mascota.getChapita().getDuenio());
  }

  @Test
  void duenioConctactaRescatista() {
    AdministracionRescates adminRescates = mock(AdministracionRescates.class);
    MascotaEncontrada mascota = new MascotaEncontrada();

    when(adminRescates.getContactoRescatista(mascota)).thenReturn(nuevoContato());

    Assertions.assertEquals(adminRescates.getContactoRescatista(mascota).getMail(),
        "tomasDias@gmail.com");
  }


  DuenioMascota nuevoDuenio() {
    RegistroDuenioMascota registro = new RegistroDuenioMascota(adminUsers);
    registro.nombre("Lucas").apellido("Gonzalez");
    registro.contacto(nuevoContato());
    registro.numeroDocumento(132123412L).tipoDocumento(TipoDocumento.DNI);
    registro.fechaNacimiento(LocalDate.now());
    registro.usuario("LucasGonzlaes").password("1Contras@Valid$a");
    return registro.mascotaOwner();
  }

  Contacto nuevoContato() {
    return new Contacto("Tomas", "Dias", "324432", "tomasDias@gmail.com");
  }

}

