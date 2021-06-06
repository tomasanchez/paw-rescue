package repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import model.mascota.encontrada.MascotaEncontrada;
import model.publicacion.Asociacion;
import model.publicacion.Publicacion;
import model.usuario.DuenioMascota;
import model.usuario.Rescatista;
import model.usuario.datospersonales.contacto.DatosContacto;
import services.ProveedorRefugios;

public class AdministracionRescates {

  List<Rescatista> rescates = new ArrayList<>();
  RepoUsers adminUsers = new RepoUsers();
  RepoPublicaciones adminPublicaciones = new RepoPublicaciones();
  ProveedorRefugios proveedorRefugios = ProveedorRefugios.instance();
  List<Asociacion> asociaciones = new ArrayList<>();

  public AdministracionRescates() {
    proveedorRefugios.loginRefugios();
  }

  public AdministracionRescates(RepoUsers adminUsers, RepoPublicaciones adminPublicaciones) {
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
    return rescates.stream().filter(rescate -> rescate.estaDentroDeUltimosDias(dias))
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


  private void notificarDuenioMascotaPerdida(DuenioMascota buscarDuenio) {
    // TODO
  }

  public List<Rescatista> getMascotasEncontradas() {
    return rescates;
  }

  public DatosContacto getContactoRescatista(MascotaEncontrada mascota) {
    return getMascotasEncontradas().stream().filter(r -> r.getMascotaEncontrada().equals(mascota))
        .findFirst().get().getDatosPersonales().getDatosContacto();
  }

  public Asociacion buscarAsociacion(MascotaEncontrada mascotaEncontrada) {
    asociaciones.sort((asociacion1, asociacion2) -> asociacion1
        .compararAsociacionesPorDistancia(asociacion2, mascotaEncontrada));

    return asociaciones.get(1);
  }


  public DatosContacto duenioEncontroSuMascota(MascotaEncontrada mascota) {
    Rescatista rescate = (Rescatista) rescates.stream()
        .filter(rescatista -> rescatista.getMascotaEncontrada() == mascota);
    return rescate.getDatosPersonales().getDatosContacto();
  }

}

