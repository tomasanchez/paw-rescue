package tools.password;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import exceptions.acceso.InvalidPasswordException;

public class CriterioPeoresContrasenias implements CriterioPassword {
  private List<String> peoresPasswords;
  private Long ultimoRefresh;

  public CriterioPeoresContrasenias() {
    refreshLista();
  }

  public Long getUltimoRefresh() {
    return this.ultimoRefresh;
  }

  @Override
  public void cumpleCriterio(String usuario, String password) {
    if (estaEntreLasPeores(password)) {
      throw new InvalidPasswordException(
          "La contrasenia esta en el top 10000 de las peores contraseñas");
    }
  }

  private boolean estaEntreLasPeores(String password) {
    return peoresPasswords.stream().anyMatch(password::contentEquals);
  }

  /**
   * Refreca la lista de peores contraseñas.
   */
  private void refreshLista() {
    String palabraLeida;
    peoresPasswords = new ArrayList<>();

    ClassLoader cl = Thread.currentThread().getContextClassLoader();
    InputStream is = cl.getResourceAsStream("tools/passwords.txt");

    if (!Objects.isNull(is)) {
      BufferedReader buffer = new BufferedReader(new InputStreamReader(is));
      try {
        while ((palabraLeida = buffer.readLine()) != null) {
          peoresPasswords.add(palabraLeida);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    } else {
      System.out.println("No pude abrir el archivo de peores contraseñas");
    }

    this.ultimoRefresh = System.currentTimeMillis();

  }
}
