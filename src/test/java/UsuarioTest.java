
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.time.LocalDate;


public class UsuarioTest {

  @Test
  public void noUsuariosVacios() {
    // No crear usuarios con mascotas vacíos
    assertThrows(NullPointerException.class, () -> {
      new RegistroUsuario().mascotaOwner();
    });
  }

  @Test
  public void registroValido() {
    assertDoesNotThrow(() -> {
      usuarioDePruebas("Test", "Passed");
    });
  }

  @Test
  public void usuarioTieneUnaMascota() {

    DuenioMascota owner = usuarioDePruebas("Usuario", "1 Mascota")
        .mascota(MascotaTest.perritaDePrueba("Maya")).mascotaOwner();
    // Tiene exactamente una mascota
    Assertions.assertTrue(owner.getCantidadMascotas() == 1);
  }

  @Test
  public void usuarioTieneMultiplesMascota() {
    // Un owner de Mascotas con 3 mascotas. "Tom, Laya y Maya"
    DuenioMascota owner = usuarioDePruebas("Usuario", "1 Mascota")
        .mascota(MascotaTest.perritaDePrueba("Maya")).mascota(MascotaTest.perritaDePrueba("Laya"))
        .mascota(MascotaTest.gatitoDePrueba("Tom")).mascotaOwner();
    // Tiene exactamente 3 mascotasd
    Assertions.assertTrue(owner.getCantidadMascotas() == 3);
  }

  /**
   * Instancia un usuario de pruebas
   * 
   * @param nombre Nombre del uisuario de prueba
   * @param apellido Apellido del usuario de Prueba
   * @return un Registro de usuario
   */
  static private RegistroUsuario usuarioDePruebas(String nombre, String apellido) {
    RegistroUsuario registro = new RegistroUsuario();
    // Registro de los datos personales
    registro.nombre(nombre).apellido(apellido).fechaNacimiento(LocalDate.now())
        .contacto(contacoDePrueba(nombre, apellido)).tipoDocumento(Documento.TipoDocumento.DNI)
        .numeroDocumento(00112233L);
    // Registro de los datos de Usuario return
    return registro.usuario(apellido + "-" + nombre).password("P@sWorD3prUb4+_!");
  }

  /**
   * Instancia un dato de contacto
   * 
   * @return un dato de contacto
   */
  static private Contacto contacoDePrueba(String nombre, String apellido) {
    return new Contacto(nombre, apellido, "+5491100000000", "mail@test.com");
  }

}

