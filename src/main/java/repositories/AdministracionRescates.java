package repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import model.mascota.encontrada.MascotaEncontrada;
import model.publicacion.Publicacion;
import model.usuario.DuenioMascota;
import model.usuario.Rescatista;

public class AdministracionRescates {

  List<Rescatista> rescates;
  AdministracionUsers adminUsers = new AdministracionUsers();
  AdministracionPublicaciones adminPublicaciones = new AdministracionPublicaciones();

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
    tratarMascotaEncontrada(rescatista.getMascotaEncontrada());
  }

  /**
   * Realiza la notificacion o publicacion de la mascota encontrada.
   * 
   * @param mascota la mascota encontrada
   */
  private void tratarMascotaEncontrada(MascotaEncontrada mascota) {
    if (mascota.tieneChapita()) {
      notificarDuenioMascotaPerdida(adminUsers.buscarDuenio(mascota));
    } else {
      this.adminPublicaciones.agregar(new Publicacion(mascota));
    }
  }

  private void notificarDuenioMascotaPerdida(DuenioMascota buscarDuenio) {}

  public List<Rescatista> getMascotasEncontradas() {
    return rescates;
  }

}

