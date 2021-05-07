import java.util.ArrayList;
import java.util.List;

public class ValidadorContrasenia {
  private List<CriterioPassword> criterios = new ArrayList<>();

  /**
   * Instancia un validador con algunos criterios.
   */
  public ValidadorContrasenia() {
    this.criterios.add(new CriterioPasswordVacia());
    this.criterios.add(new CriterioPeoresContrasenias());
    this.criterios.add(new CriterioLargoMinimo(8));

  }

  /**
   * Valida la contraseña con toda una lista de criterios.
   * 
   * @param usuario el usuario a validar.
   * @param password la password a validar.
   */
  public void validarPassword(String usuario, String password) {
    criterios.stream().forEach(criterio -> criterio.cumpleCriterio(usuario, password));
  }
}
