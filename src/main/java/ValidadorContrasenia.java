import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ValidadorContrasenia {
  private List<String> listaPalabras;
  private Long ultimoRefresh;

  public ValidadorContrasenia(){
    refreshLista();
  }
  public boolean validarContrasenia(String palabra) {
    if(System.currentTimeMillis()- ultimoRefresh > 1000*60*60*24){
      refreshLista();
    }//si paso un dia
    return listaPalabras.stream().anyMatch(palabra::contentEquals);
  }

  private void refreshLista() {
    String filePath = System.getProperty("user.dir") + "/src/files/";
    String palabraLeida;
    listaPalabras = new ArrayList<>();
    try (FileReader reader = new FileReader(filePath + "10k-worst-passwords.txt");
         BufferedReader buffer = new BufferedReader(reader)) {

      while ((palabraLeida = buffer.readLine()) != null) {
        listaPalabras.add(palabraLeida);
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
