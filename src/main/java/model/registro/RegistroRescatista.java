package model.registro;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import model.mascota.encontrada.MascotaEncontrada;
import model.refugio.Refugio;
import model.usuario.Rescatista;
import repositories.AdministracionRescates;
import services.ProveedorRefugios;

/**
 * Registro de Rescates - Builder de Rescatista.
 */
public class RegistroRescatista extends RegistroDatosPersonales {

  /**
   * Dependencia de repositorio.
   * 
   * @since Entrega 2.
   */
  private AdministracionRescates adminRescastes;

  /**
   * Dependencia de repositorio.
   * 
   * @since Entrega 2.
   */

  private MascotaEncontrada mascotaEncontrada;
  
  private String direccionHogarDeTransito;

  private Boolean rescatistaAlbergaMascota= false;
  
  private Refugio refugioAsignado; 
  
  ProveedorRefugios proveedorRefugios = ProveedorRefugios.instance();

  public RegistroRescatista(AdministracionRescates adminRescastes) {
    this.adminRescastes = adminRescastes;
  }

  public void mascotaEncontrada(MascotaEncontrada mascotaEncontrada) {
    this.mascotaEncontrada = Objects.requireNonNull(mascotaEncontrada);
  }
  
  public void albergarMascota(String direccionDelRescatista) {
    this.direccionHogarDeTransito= direccionDelRescatista;
    this.rescatistaAlbergaMascota= true;
  }
  
  public void asignarRefugio(Refugio refugio) {
    this.refugioAsignado= refugio;
    this.direccionHogarDeTransito= refugio.getDireccion();
    this.rescatistaAlbergaMascota= false;
;  }
  
  public List<Refugio> buscarRefugios(MascotaEncontrada mascota) {
    List<Refugio> refugioList = proveedorRefugios.getAllRefugios();
    return refugioList.stream()
        .filter(cada -> cada.getAdmisiones().contains(mascota.getTipoMascota()))
        .collect(Collectors.toList());

  }
  
  
  
  public Rescatista generarRescate() {
    Rescatista rescatista = instanciarRescatista();
    getAdminRescastes().registrarRescate(rescatista);
    return rescatista;
  }

  private Rescatista instanciarRescatista() {
    return new Rescatista(datosPersonales(), mascotaEncontrada, rescatistaAlbergaMascota, direccionHogarDeTransito, refugioAsignado);
  }

  public RegistroRescatista setAdminRescastes(AdministracionRescates adminRescastes) {
    this.adminRescastes = adminRescastes;
    return this;
  }

  public AdministracionRescates getAdminRescastes() {
    return this.adminRescastes;
  }

  public String getHogarTransitorio() {
    return direccionHogarDeTransito;
  }

  public Refugio getRefugioAsignado() {
    return refugioAsignado;
  }
}


