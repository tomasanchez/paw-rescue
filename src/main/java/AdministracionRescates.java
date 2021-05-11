import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AdministracionRescates {

    List<Rescatista> rescatistas;
    
    public AdministracionRescates() {
      this.rescatistas = new ArrayList<>();
    }

    /**
     * Obtiene las mascotas encontradas en los últimos días.
     *
     * @param dias ultimos días en los que filtrar
     * @return las mascotas filtradas
     */
    List<Rescatista> mascotasEncontradas(long dias) {
      return rescatistas.stream()
        .filter(rescatista -> rescatista.mascotaEncontrada.fecha.isAfter(LocalDate.now().minusDays(dias)))
        .collect(Collectors.toList());
    }
    /**
     * Guarda la información de una mascota rescatada.
     *
     * @param rescatista el rescatista rescatada
     */
    public void registrarRescate(Rescatista rescatista) {
      this.rescatistas.add(rescatista);
    }
    
    
    public List<Rescatista> getMascotasEncontradas() {
      return this.rescatistas;
    }


  }


