package repositories;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import db.PersistentEntitySet;
import model.mascota.encontrada.MascotaEncontrada;
import model.refugio.Refugio;
import model.usuario.Rescate;
import model.usuario.datospersonales.contacto.DatosContacto;
import services.ProveedorRefugios;

public class RepoRescates extends PersistentEntitySet<Rescate> {

  RepoUsers adminUsers = new RepoUsers();
  RepoPublicaciones adminPublicaciones = new RepoPublicaciones();
  ProveedorRefugios proveedorRefugios = ProveedorRefugios.instance();

  private static RepoRescates instancia;

  public static RepoRescates getInstance() {
    if (instancia == null) {
      instancia = new RepoRescates();
    }
    return instancia;
  }

  public RepoRescates() {
    
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
    return getEntitySet().stream().filter(rescate -> rescate.estaDentroDeUltimosDias(dias))
        .collect(Collectors.toList());
  }

  @Override
  public Rescate createEntity(Rescate rescate) {
    entityManager().persist(rescate.getMascotaEncontrada());
    return super.createEntity(rescate);
  }

  /**
   * Guarda la información de una mascota rescatada.
   *
   * @param rescate el rescatista rescatada
   */
  public void addRescate(Rescate rescate) {
    Objects.requireNonNull(rescate.getMascotaEncontrada());
    createEntity(rescate);
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
    return (Rescate) getEntitySet().stream().filter(rescatista -> rescatista.getMascotaEncontrada().equals(mascota))
        .collect(Collectors.toList()).get(0);
  }

  public List<Refugio> buscarRefugios(MascotaEncontrada mascota) {
    proveedorRefugios.loginRefugios();
    List<Refugio> refugioList = proveedorRefugios.getAllRefugios();
    return refugioList.stream().filter(cada -> cada.getAdmisiones().contains(mascota.getTipoMascota()))
        .collect(Collectors.toList());
  }

  public List<Rescate> getRescates() {
    return getEntitySet();
  }

  /**
   * Obtiene los rescates en los últimos N días.
   *
   * @param dias ultimos días en los que filtrar
   * @return las mascotas filtradas
   */
  List<Rescate> getRescatesEnLosUltimosDias(long dias) {
    return getRescates().stream().filter(rescate -> rescate.estaDentroDeUltimosDias(dias)).collect(Collectors.toList());
  }

  /**
   * Obtiene las mascotas encontradas.
   *
   * @return un listado de mascotas.
   */
  public List<MascotaEncontrada> getMascotasEncontradas() {
    return getEntitySet().stream().map(Rescate::getMascotaEncontrada).collect(Collectors.toList());
  }

}
