package model.registro;


import java.util.Objects;
import model.mascota.encontrada.MascotaEncontrada;
import model.refugio.Refugio;
import model.usuario.Rescatista;

/**
 * Registro de Rescates - Builder de Rescatista.
 */
public class RegistroRescatista extends RegistroDatosPersonales {

  /**
   * Dependencia de repositorio.
   * 
   * @since Entrega 2.
   */

  private MascotaEncontrada mascotaEncontrada;

  private String direccionHogarDeTransito;

  private Boolean rescatistaAlbergaMascota = false;

  private Refugio refugioAsignado;

  public void mascotaEncontrada(MascotaEncontrada mascotaEncontrada) {
    this.mascotaEncontrada = Objects.requireNonNull(mascotaEncontrada);
  }

  public void albergarMascota(String direccionDelRescatista) {
    this.direccionHogarDeTransito = direccionDelRescatista;
    this.rescatistaAlbergaMascota = true;
  }

  public void asignarRefugio(Refugio refugio) {
    this.refugioAsignado = refugio;
    this.direccionHogarDeTransito = refugio.getDireccion();
    this.rescatistaAlbergaMascota = false;
  }

  public Rescatista generarRescate() {
    Rescatista rescatista = instanciarRescatista();
    return rescatista;
  }

  private Rescatista instanciarRescatista() {
    return new Rescatista(datosPersonales(), mascotaEncontrada, rescatistaAlbergaMascota,
        direccionHogarDeTransito, refugioAsignado);
  }

  public String getHogarTransitorio() {
    return direccionHogarDeTransito;
  }

  public Refugio getRefugioAsignado() {
    return refugioAsignado;
  }
}


