package model.registro;


import java.util.Objects;
import model.mascota.encontrada.MascotaEncontrada;
import model.refugio.Refugio;
import model.usuario.Rescate;
import model.usuario.datospersonales.DatosPersonales;

/**
 * Registro de Rescates - Builder de Rescatista.
 */
public class RegistroRescate{

  /**
   * Dependencia de repositorio.
   *
   * @since Entrega 2.
   */

  private DatosPersonales datosRescatista;

  private String domicilioRescatista;

  private MascotaEncontrada mascotaEncontrada;

  private Boolean rescatistaAlbergaMascota;

  private Refugio refugioAsignado;


  public RegistroRescate datosRescatista(DatosPersonales datosRescatista) {
    this.datosRescatista= Objects.requireNonNull(datosRescatista);
    return this;
  }

  public RegistroRescate domicilioRescatista(String domicilio) {
    this.domicilioRescatista= Objects.requireNonNull(domicilio);
    return this;
  }

  public RegistroRescate mascotaEncontrada(MascotaEncontrada mascotaEncontrada) {
    this.mascotaEncontrada = Objects.requireNonNull(mascotaEncontrada);
    return this;
  }

  public RegistroRescate albergaMascota(boolean rta) {
    this.rescatistaAlbergaMascota= Objects.requireNonNull(rta);
    return this;
  }

  public RegistroRescate asignarRefugio(Refugio refugio) {
    this.refugioAsignado = refugio;
    return this;
  }

  //

  public Rescate generarRescate() {
    return new Rescate(this.datosRescatista,this.domicilioRescatista,this.mascotaEncontrada,
      this.rescatistaAlbergaMascota,this.refugioAsignado);
  }

}
