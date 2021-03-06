package app;

import java.time.LocalDate;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import exceptions.usuario.UsuarioYaExisteException;
import model.registro.RegistroDuenioMascota;
import model.usuario.Privilegio;
import model.usuario.Usuario;
import model.usuario.datospersonales.contacto.DatosContacto;
import model.usuario.datospersonales.documento.TipoDocumento;
import repositories.RepoUsers;

public class SetUp implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {


  public static void main(String[] args) {
    new SetUp().bootStrap();
  }

  /**
   * Instala un usuario administrador.
   */
  public void bootStrap() {
    Usuario admin = newAdmin();
    admin.setPassword("admin");
    admin.setPrivilege(Privilegio.ADMIN);

    try {
      withTransaction(() -> {
        RepoUsers.getInstance().createEntity(admin);
      });
    } catch (UsuarioYaExisteException e) {
      System.out.println("Admin ya existe!");
    }
  }

  Usuario newAdmin() {
    RegistroDuenioMascota registro = new RegistroDuenioMascota(RepoUsers.getInstance());
    registro.nombre("N/A").apellido("N/A");
    registro.contacto(nuevoContato());
    registro.numeroDocumento(99999999L).tipoDocumento(TipoDocumento.DNI);
    registro.fechaNacimiento(LocalDate.now());
    registro.usuario("admin").password("1Contras@Valid$a");
    return registro.mascotaOwner();
  }

  DatosContacto nuevoContato() {
    return new DatosContacto("N/A", "N/A", "N/A", "admin@rescate.patitas.com");
  }

}
