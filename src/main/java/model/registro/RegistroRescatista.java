package model.registro;

import java.util.Objects;
import model.mascota.encontrada.MascotaEncontrada;
import model.publicacion.Publicacion;
import model.usuario.Rescatista;
import repositories.AdministracionPublicaciones;
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

  public RegistroRescatista(AdministracionRescates adminRescastes) {
    this.adminRescastes = adminRescastes;
  }

  public void mascotaEncontrada(MascotaEncontrada mascotaEncontrada) {
    this.mascotaEncontrada = Objects.requireNonNull(mascotaEncontrada);
  }

  
  public void generarRescate() {
    Rescatista rescatista = new Rescatista(datosPersonales(), mascotaEncontrada);
    adminRescastes.registrarRescate(rescatista);
  }

}


