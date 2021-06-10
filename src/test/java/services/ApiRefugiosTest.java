package services;

import model.refugio.Refugio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import api.response.HogaresResponse;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

public class ApiRefugiosTest {

  private ProveedorRefugios proveedorRefugios;

  @BeforeEach
  void initProveedorRefugios() {
    proveedorRefugios = mock(ProveedorRefugios.class);
    proveedorRefugios.loginRefugios();
  }

  @Test
  void loginTest() {
    verify(proveedorRefugios).loginRefugios();
  }

  @Test
  void obtenerHogares() {
    when(proveedorRefugios.getHogares("offset", "2")).thenReturn(new HogaresResponse());
    HogaresResponse hogaresResponse = proveedorRefugios.getHogares("offset", "2");
    Assertions.assertNotNull(hogaresResponse);
    verify(proveedorRefugios).getHogares("offset", "2");
  }

  @Test
  void obtenerTodosLosRefugios() {
    List<Refugio> listaTest = new ArrayList<>();
    when(proveedorRefugios.getAllRefugios()).thenReturn(listaTest);
    List<Refugio> list = proveedorRefugios.getAllRefugios();
    verify(proveedorRefugios).getAllRefugios();
    Assertions.assertNotNull(list);
  }
/* Test sin MOCK
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
  */
}
