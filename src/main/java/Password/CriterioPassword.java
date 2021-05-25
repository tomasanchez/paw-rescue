package Password;
public interface CriterioPassword {

  /**
   * Verifica si cumple el criterio de validación.
   * 
   * @param usuario  el nombre de usuario.
   * @param password la password a validar.
   */
  void cumpleCriterio(String usuario, String password);
}
