package repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import model.mascota.encontrada.MascotaEncontrada;
import model.publicacion.Publicacion;
import model.refugio.Refugio;
import model.usuario.Contacto;
import model.usuario.DuenioMascota;
import model.usuario.Rescatista;
import services.ProveedorRefugios;

public class AdministracionRescates {

  List<Rescatista> rescates = new ArrayList<>();
  AdministracionUsers adminUsers = new AdministracionUsers();
  AdministracionPublicaciones adminPublicaciones = new AdministracionPublicaciones();
  ProveedorRefugios proveedorRefugios = ProveedorRefugios.instance();

  public AdministracionRescates() {
    proveedorRefugios.loginRefugios();
  }

  public AdministracionRescates(AdministracionUsers adminUsers,
                                AdministracionPublicaciones adminPublicaciones) {
    this.adminUsers = adminUsers;
    this.adminPublicaciones = adminPublicaciones;
    proveedorRefugios.loginRefugios();
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
    identificarMascota(rescatista.getMascotaEncontrada());

    if (!(rescatista.puedeAlbergarMascota())) {
      buscarHogar(rescatista.getMascotaEncontrada());
    }
  }

  /**
   * Realiza la notificacion o publicacion de la mascota encontrada.
   * 
   * @param mascota la mascota encontrada
   */
  private void identificarMascota(MascotaEncontrada mascota) {
    if (mascota.tieneChapita()) {
      notificarDuenioMascotaPerdida(adminUsers.buscarDuenio(mascota));
    } else {
      this.adminPublicaciones.agregar(new Publicacion(mascota));
    }
  }

  private List<Refugio> buscarHogar(MascotaEncontrada mascota) {
    List<Refugio> refugioList = proveedorRefugios.getAllRefugios();
    return refugioList.stream().filter(cada -> cada.getAdmisiones().contains(mascota.getTipoMascota())).collect(Collectors.toList());

  }

  private void notificarDuenioMascotaPerdida(DuenioMascota buscarDuenio) {
    // TODO
  }

  public List<Rescatista> getMascotasEncontradas() {
    return rescates;
  }

  public Contacto getContactoRescatista(MascotaEncontrada mascota) {
    return getMascotasEncontradas().stream().filter(r -> r.getMascotaEncontrada().equals(mascota))
      .findFirst().get().getDatosPersonales().getContacto();
  }

  public void buscarAsociacion(MascotaEncontrada mascotaEncontrada) {
    // TODO implementar
  }

  public Contacto duenioEncontroSuMascota(MascotaEncontrada mascota) {
    Rescatista rescate = (Rescatista) rescates.stream()
        .filter(rescatista -> rescatista.getMascotaEncontrada() == mascota);
    return rescate.getDatosPersonales().getContacto();
  }
  
}

