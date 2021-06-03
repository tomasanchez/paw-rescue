import Rest.Response.HogaresResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ApiRefugiosTest {
  @Test
  void obtenerRefugios(){
    ProveedorRefugios proveedorRefugios = new ProveedorRefugios();
    proveedorRefugios.loginRefugios();
    HogaresResponse hogaresResponse = proveedorRefugios.getRefugios("offset","2");
    Assertions.assertEquals("2",hogaresResponse.getOffset());
    Assertions.assertNotNull(hogaresResponse.getHogares());
  }
}
