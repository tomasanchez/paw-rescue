package repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import model.usuario.Rescatista;

public class AdministracionRescates {

  List<Rescatista> rescates;

  public AdministracionRescates() {
    this.rescates = new ArrayList<>();
  }

  /**
   * Obtiene las mascotas encontradas en los últimos días.
   *
   * @param dias ultimos días en los que filtrar
   * @return las mascotas filtradas
   */
  List<Rescatista> mascotasEncontradas(long dias) {
    return rescates.stream().filter(rescate -> rescate.compararFechaMascotaEncontrada(dias))
        .collect(Collectors.toList());
  }

  /**
   * Guarda la información de una mascota rescatada.
   *
   * @param rescatista el rescatista rescatada
   */
  public void registrarRescate(Rescatista rescatista) {
    rescates.add(rescatista);
  }


  public List<Rescatista> getMascotasEncontradas() {
    return rescates;
  }

}


