
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class UsuarioTest {

  @Test
  public void noUsuariosVacios() {
    // No crear usuarios con mascotas vacíos
    assertThrows(NullPointerException.class, () -> {
      new RegistroUsuario().duenioMascota();
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
    // Listado de una unica mascota
    List<Mascota> mascotas = Arrays.asList(new Mascota[] { MascotaTest.perritaDePrueba("Maya") });
    // Tiene exactamente una mascota
    Assertions
        .assertTrue(usuarioDePruebas("Usuario", "1 Mascota").mascotas(mascotas).duenioMascota().mascotas.size() == 1);
  }

  @Test
  public void usuarioTieneMultiplesMascota() {
    // Listado de una unica mascota
    List<Mascota> mascotas = Arrays.asList(new Mascota[] { MascotaTest.perritaDePrueba("Maya"),
        MascotaTest.gatitoDePrueba("Tom"), MascotaTest.perritaDePrueba("Lola") });
    // Tiene exactamente una mascota
    Assertions.assertTrue(usuarioDePruebas("Usuario", "Muchas Mascotas").mascotas(mascotas).duenioMascota().mascotas
        .size() == mascotas.size());
  }

  /**
   * Instancia un usuario de pruebas
   * 
   * @param nombre   Nombre del uisuario de prueba
   * @param apellido Apellido del usuario de Prueba
   * @return un Registro de usuario
   */
  static private RegistroUsuario usuarioDePruebas(String nombre, String apellido) {
    RegistroUsuario registro = new RegistroUsuario();
    // Registro de los datos personales
    registro.nombre(nombre).apellido(apellido).fechaNacimiento(LocalDate.now())
        .contacto(contacoDePrueba(nombre, apellido)).tipoDocumento(Documento.TipoDocumento.DNI)
        .numeroDocumento(00112233L);
    // Registro de los datos de Usuario
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
