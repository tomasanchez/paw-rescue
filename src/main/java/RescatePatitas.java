import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RescatePatitas {
  List<MascotaEncontrada> mascotasEncontradas;
  List<Usuario> usuarios;

  public RescatePatitas() {
    this.mascotasEncontradas = new ArrayList<MascotaEncontrada>();
    this.usuarios = new ArrayList<Usuario>();
  }

  /**
   * Obtiene las mascotas encontradas en los últimos días
   * 
   * @param dias ultimos días en los que filtrar
   * @return las mascotas filtradas
   */
  List<MascotaEncontrada> mascotasEncontradas(long dias) {
    return mascotasEncontradas.stream().filter(mascota -> mascota.fecha.isAfter(LocalDate.now().minusDays(dias)))
        .collect(Collectors.toList());
  }

  /**
   * Guarda la información de una mascota rescatada
   * 
   * @param mascota la mascota rescatada
   */
  public void registrarRescate(MascotaEncontrada mascota) {
    this.mascotasEncontradas.add(mascota);
  }

}
