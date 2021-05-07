public class Administrador extends Usuario {

  /**
   * Mascotas registadas
   *
   * @param datosPeronales
   * @param usuario
   * @param password
   * @since 1.0
   */
  public Administrador(Persona datosPeronales, String usuario, String password) {
    super(datosPeronales, usuario, password);
  }
}
