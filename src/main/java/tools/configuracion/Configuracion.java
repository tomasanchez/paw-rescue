package tools.configuracion;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuracion {

  private Properties properties;

  public Configuracion() {
    try {
      cargarArchivoProperties();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void cargarArchivoProperties() throws IOException {
    properties = new Properties();
    InputStream file = new FileInputStream("Configuracion.properties");
    properties.load(file);
  }

  public String getProperty(String key) {
    if (properties == null) {
      try {
        cargarArchivoProperties();
      } catch (IOException e) {
        e.printStackTrace();
        return "";
      }
    }

    return properties.getProperty(key);
  }
}
