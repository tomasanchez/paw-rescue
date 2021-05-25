import exeptions.InvalidPasswordException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    String filePath = System.getProperty("user.dir") + "/src/files/";
    String palabraLeida;
    peoresPasswords = new ArrayList<>();
    try (
        FileReader reader =
            new FileReader(filePath + "10k-worst-passwords.txt"),StandardCharsets.UTF_8);
        BufferedReader buffer = new BufferedReader(reader)) {

      while ((palabraLeida = buffer.readLine()) != null) {
        peoresPasswords.add(palabraLeida);
      }
    } catch (FileNotFoundException e) {
      System.out.println("No pude abrir el archivo por el siguiente motivo: " + e.getMessage());
    } catch (IOException e) {
      System.out.println("Error leyendo el archivo por el siguiente motivo: " + e.getMessage());
    } finally {
      this.ultimoRefresh = System.currentTimeMillis();
    }
  }
}
