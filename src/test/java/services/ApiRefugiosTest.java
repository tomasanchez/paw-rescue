package services;

import model.refugio.Refugio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import api.response.HogaresResponse;

import java.util.List;

public class ApiRefugiosTest {
  @Test
  void obtenerHogares() {
    ProveedorRefugios proveedorRefugios = ProveedorRefugios.instance();
    proveedorRefugios.loginRefugios();
    HogaresResponse hogaresResponse = proveedorRefugios.getHogares("offset", "2");
    Assertions.assertEquals("2", hogaresResponse.getOffset());
    Assertions.assertNotNull(hogaresResponse.getHogares());
  }

  @Test
  void obtenerTodosLosRefugios() {
    ProveedorRefugios proveedorRefugios = ProveedorRefugios.instance();
    proveedorRefugios.loginRefugios();
    List<Refugio> list = proveedorRefugios.getAllRefugios();
    Assertions.assertEquals(list.stream().findFirst().get().getClass(), Refugio.class);
    Assertions.assertNotNull(list);
  }
}
