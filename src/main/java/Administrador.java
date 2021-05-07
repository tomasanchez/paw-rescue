public class Administrador extends Usuario {

  /**
   * Mascotas registadas.
   *
   * @param datosPeronales Nombre, DNI, etc.
   * @param usuario        nombre de usuario.
   * @param password       password válida.
   * @since 1.0
   */
  public Administrador(Persona datosPeronales, String usuario, String password) {
    super(datosPeronales, usuario, password);
  }
}
