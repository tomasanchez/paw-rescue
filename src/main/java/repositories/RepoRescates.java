package repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import model.mascota.encontrada.MascotaEncontrada;
import model.publicacion.Asociacion;
import model.refugio.Refugio;
import model.usuario.Rescate;
import model.usuario.datospersonales.contacto.DatosContacto;
import services.ProveedorRefugios;

public class RepoRescates {

  List<Rescate> rescates = new ArrayList<>();
  RepoUsers adminUsers = new RepoUsers();
  RepoPublicaciones adminPublicaciones = new RepoPublicaciones();
  ProveedorRefugios proveedorRefugios = ProveedorRefugios.instance();
  List<Asociacion> asociaciones = new ArrayList<>();

  public RepoRescates() {
    proveedorRefugios.loginRefugios();
  }

  public RepoRescates(RepoUsers adminUsers, RepoPublicaciones adminPublicaciones) {
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
  List<Rescate> mascotasEncontradas(long dias) {
    return rescates.stream().filter(rescate -> rescate.estaDentroDeUltimosDias(dias))
        .collect(Collectors.toList());
  }

  /**
   * Guarda la información de una mascota rescatada.
   *
   * @param rescate el rescatista rescatada
   */
  public void addRescate(Rescate rescate) {
    Objects.requireNonNull(rescate.getMascotaEncontrada());
    rescates.add(rescate);
  }


  public Asociacion buscarAsociacion(MascotaEncontrada mascotaEncontrada) {
    ordenarAsociacionesPorDistancia(mascotaEncontrada);
    return asociaciones.get(1);
  }

  private void ordenarAsociacionesPorDistancia(MascotaEncontrada mascotaEncontrada) {
    asociaciones.sort((asociacion1, asociacion2) -> asociacion1
        .compararAsociacionesPorDistancia(asociacion2, mascotaEncontrada));
  }

  /**
   * Recupera el contancto de un rescate registrado.
   *
   * @param mascota la mascota encontrada
   * @return el contacto
   */

  public DatosContacto contactoRescatista(MascotaEncontrada mascota) {
    return buscarRescatista(mascota).getDatosRescatista().getDatosContacto();
  }

  private Rescate buscarRescatista(MascotaEncontrada mascota) {
    return (Rescate) rescates.stream().filter(rescatista -> rescatista.getMascotaEncontrada().equals(mascota));
  }

  public List<Refugio> buscarRefugios(MascotaEncontrada mascota) {
    List<Refugio> refugioList = proveedorRefugios.getAllRefugios();
    return refugioList.stream()
        .filter(cada -> cada.getAdmisiones().contains(mascota.getTipoMascota()))
        .collect(Collectors.toList());
  }

  public List<Rescate> getRescates() {
    return rescates;
  }

  public void setRescates(List<Rescate> rescates) {
    this.rescates = rescates;
  }

  /**
   * Obtiene los rescates en los últimos N días.
   *
   * @param dias ultimos días en los que filtrar
   * @return las mascotas filtradas
   */
  List<Rescate> getRescatesEnLosUltimosDias(long dias) {
    return getRescates().stream().filter(rescate -> rescate.estaDentroDeUltimosDias(dias))
        .collect(Collectors.toList());
  }

  /**
   * Obtiene las mascotas encontradas.
   *
   * @return un listado de mascotas.
   */
  public List<MascotaEncontrada> getMascotasEncontradas() {
    return rescates.stream().map(Rescate::getMascotaEncontrada).collect(Collectors.toList());
  }



}


