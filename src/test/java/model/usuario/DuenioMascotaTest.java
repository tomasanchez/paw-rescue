package model.usuario;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import model.mascota.Mascota;
import model.mascota.Sexo;
import model.mascota.TipoMascota;
import model.registro.RegistroDuenioMascota;
import model.usuario.datospersonales.contacto.DatosContacto;
import model.usuario.datospersonales.documento.TipoDocumento;
import repositories.RepoUsers;

public class DuenioMascotaTest implements WithGlobalEntityManager {

  private Usuario duenio;
  private RepoUsers adminUsers;

  @BeforeEach
  void iniciarDuenio() {
    entityManager().getTransaction().begin();
    adminUsers = new RepoUsers();
    duenio = nuevoDuenio();
  }

  @AfterEach
  void endTransaction() {
    entityManager().getTransaction().rollback();
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
  public void duenioMascotasRegistraUnaMascotaYAhoraTiene1MascotaMas() {
    duenio.registrarMascota(gato());
    Assertions.assertEquals(duenio.getCantidadMascotas(), 1);
  }

  @Test
  public void duenioMascotasTieneMasDeUnaMascota() {
    duenio.registrarMascota(perra());
    duenio.registrarMascota(gato());
    Assertions.assertEquals(duenio.getCantidadMascotas(), 2);
  }

  Mascota gato() {
    return new Mascota("copito", "copi", TipoMascota.GATO, 6, Sexo.MACHO,
        "Tamaño grande y de pelo largo", null, null);
  }

  Mascota perra() {
    return new Mascota("negra", "negrita", TipoMascota.PERRO, 2, Sexo.HEMBRA,
        "Pelo corto y tamaño madiano", null, null);
  }

  Usuario nuevoDuenio() {
    RegistroDuenioMascota registro = new RegistroDuenioMascota(adminUsers);
    registro.nombre("Lucas").apellido("Gonzalez");
    registro.contacto(nuevoContato());
    registro.numeroDocumento(132123412L).tipoDocumento(TipoDocumento.DNI);
    registro.fechaNacimiento(LocalDate.now());
    registro.usuario("LucasGonzlaes").password("1Contras@Valid$a");
    return registro.mascotaOwner();
  }

  DatosContacto nuevoContato() {
    return new DatosContacto("Tomas", "Dias", "324432", "tomasDias@gmail.com");
  }

}
