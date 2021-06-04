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

  private Boolean albergaMascota;
  
  private String hogarTransitorio;
  
  ProveedorRefugios proveedorRefugios = ProveedorRefugios.instance();

  public RegistroRescatista(AdministracionRescates adminRescastes) {
    this.adminRescastes = adminRescastes;
  }

  public void mascotaEncontrada(MascotaEncontrada mascotaEncontrada) {
    this.mascotaEncontrada = Objects.requireNonNull(mascotaEncontrada);
  }

  public void puedeAlbergarMascota(Boolean value) {
    this.albergaMascota = value;
  }

  
  public void albergarMascota(String direccion) {
    this.hogarTransitorio= direccion;
  }
  
  public void asignarRefugio(Refugio refugio) {
    this.hogarTransitorio= refugio.getDireccion();
  }
  
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
    return new Rescatista(datosPersonales(), mascotaEncontrada, albergaMascota);
  }

  public RegistroRescatista setAdminRescastes(AdministracionRescates adminRescastes) {
    this.adminRescastes = adminRescastes;
    return this;
  }

  public AdministracionRescates getAdminRescastes() {
    return this.adminRescastes;
  }

  public String getHogarTransitorio() {
    return hogarTransitorio;
  }
}


