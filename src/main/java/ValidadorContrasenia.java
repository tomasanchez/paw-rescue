import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ValidadorContrasenia {

  private List<String> peoresPasswords;
  private Long ultimoRefresh;

  /**
   * Mínimos caracteres necesarios.
   * 
   * @since 2.0
   */
  private final int LONGITUD_MINIMA;

  /**
   * Un validador con el standard de almenos 8 cartácteres.
   * 
   * @since 1.0
   */
  public ValidadorContrasenia() {
    this(8);
  }

  /**
   * Instancia un validador con los caracteres a elección.
   * 
   * @param minimaLongitud la minima longitud de caracteres de una password
   * 
   * @since 2.0
   */
  public ValidadorContrasenia(int minimaLongitud) {
    this.LONGITUD_MINIMA = minimaLongitud;
    refreshLista();
  }

  public boolean validarContrasenia(String password) {

    // Recarega la lista si pasó un dia
    if (System.currentTimeMillis() - ultimoRefresh > 1000 * 60 * 60 * 24)
      refreshLista();

    return esPasswordVacia(password) || estaEntreLasPeores(password) || esPasswordCorta(password);
  }

  /**
   * Verifica que la password no esté vacía
   * 
   * @param password la password a verificar
   * @return si es NULL.
   */
  private boolean esPasswordVacia(String password) {
    return Objects.isNull(password);
  }

  /**
   * Valia si cumple con la mínima longitud
   * 
   * @param password la password a validar
   * @return Si la password tiene pocos caracteres
   * @since 2.0
   */
  private boolean esPasswordCorta(String password) {
    return password.length() < LONGITUD_MINIMA;
  }

  /**
   * Valia si cumple con no estar un la lista
   * 
   * @param password la password a validar
   * @return Si la password está entre las peores.
   * @since 1.0
   */
  private boolean estaEntreLasPeores(String password) {
    return peoresPasswords.stream().anyMatch(password::contentEquals);
  }

  /**
   * Obtiene el top 10000 peores passwords
   * 
   * @since 1.0
   */
  private void refreshLista() {
    String filePath = System.getProperty("user.dir") + "/src/files/";
    String palabraLeida;
    peoresPasswords = new ArrayList<>();
    try (FileReader reader = new FileReader(filePath + "10k-worst-passwords.txt", Charset.defaultCharset());
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
