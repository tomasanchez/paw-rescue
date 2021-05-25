public class Administrador extends Usuario {

  /**
   * Mascotas registadas.
   *
   *
   * @param persona
   * @param usuario nombre de usuario.
   * @param password password válida.
   * @since 1.0
   */
  public Administrador(DatosPersonales persona, String usuario, String password) {
    super( usuario, password);
  }

  /**
   * Agrega una caracteristica a una mascota.
   * 
   * @param mascota la mascota a agregar una Caracteristica
   * @param caracteristica la caracteristica a agregar
   * @return la mascota modificada.
   */
  public Mascota caracterizar(Mascota mascota, Caracteristica caracteristica) {
    return mascota.agregarCaracteristica(caracteristica);
  }
  
  
}
