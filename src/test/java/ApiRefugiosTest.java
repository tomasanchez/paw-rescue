import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ApiRefugiosTest {
  @Test
  void obtenerRefugios(){
    ProveedorRefugios proveedorRefugios = new ProveedorRefugios();
    proveedorRefugios.loginRefugios();
    proveedorRefugios.getRefugios("a","a");
    Assertions.assertEquals(1,1);
  }
}
