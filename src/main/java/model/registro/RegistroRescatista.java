package model.registro;

import java.util.Objects;
import model.mascota.encontrada.MascotaEncontrada;
import model.usuario.Rescatista;
import repositories.AdministracionRescates;

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

  public RegistroRescatista(AdministracionRescates adminRescastes) {
    this.adminRescastes = adminRescastes;
  }

  public void mascotaEncontrada(MascotaEncontrada mascotaEncontrada) {
    this.mascotaEncontrada = Objects.requireNonNull(mascotaEncontrada);
  }

  public void puedeAlbergarMascota(Boolean value) {
    this.albergaMascota = value;
  }

  public Rescatista generarRescate() {
    Rescatista rescatista = new Rescatista(datosPersonales(), mascotaEncontrada, albergaMascota);
    adminRescastes.registrarRescate(rescatista);
    return rescatista;
  }

}


