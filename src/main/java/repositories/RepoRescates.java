package repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import model.mascota.encontrada.MascotaEncontrada;
import model.usuario.Rescatista;
import model.usuario.datospersonales.contacto.DatosContacto;

/**
 * Repositorio de Rescates
 * 
 * @version 1.0
 * @since 06.05.2021
 * @author Tomás Sánchez
 */
public class RepoRescates {
  List<Rescatista> rescates = new ArrayList<>();

  public List<Rescatista> getRescates() {
    return rescates;
  }

  public void setRescates(List<Rescatista> rescates) {
    this.rescates = rescates;
  }

  /**
   * Obtiene los rescates en los últimos N días.
   *
   * @param dias ultimos días en los que filtrar
   * @return las mascotas filtradas
   */
  List<Rescatista> getRescatesEnLosUltimosDias(long dias) {
    return getRescates().stream().filter(rescate -> rescate.compararFechaMascotaEncontrada(dias))
        .collect(Collectors.toList());
  }

  /**
   * Obtiene las mascotas encontradas.
   * 
   * @return un listado de mascotas.
   */
  List<MascotaEncontrada> getMascotasEncontradas() {
    return getRescates().stream().map(Rescatista::getMascotaEncontrada)
        .collect(Collectors.toList());
  }

  /**
   * Guarda la información de una mascota rescatada.
   *
   * @param rescatista el rescatista rescatada
   */
  public void addRescate(Rescatista rescatista) {
    getRescates().add(rescatista);
  }

  /**
   * Recupera el contancto de un rescate registrado.
   * 
   * @param mascota la mascota encontrada
   * @return el contacto
   */
  public DatosContacto getContactoRescatista(MascotaEncontrada mascota) {
    Optional<Rescatista> rescatista =
        getRescates().stream().filter(r -> r.getMascotaEncontrada().equals(mascota)).findFirst();

    if (rescatista.isPresent()) {
      return rescatista.get().getDatosPersonales().getDatosContacto();
    } else {
      return null;
    }
  }

}
