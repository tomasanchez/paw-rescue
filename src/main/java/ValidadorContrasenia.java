import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ValidadorContrasenia {
  private List<CriterioPassword> criterios = new ArrayList<>();

  public ValidadorContrasenia() {
    this.criterios.add(new CriterioPeoresContrasenias());
    this.criterios.add(new CriterioLargoMinimo(8));
    this.criterios.add(new CriterioPasswordVacia());
    
  }
  public void ValidarContrasenia(String usuario, String password){
    criterios.stream().forEach(criterio ->criterio.cumpleCriterio(usuario,password));
      
    
  }
}
