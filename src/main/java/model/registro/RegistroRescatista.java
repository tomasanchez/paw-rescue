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
  private AdministracionPublicaciones adminPublicaciones;

  private MascotaEncontrada mascotaEncontrada;

  public RegistroRescatista(AdministracionRescates adminRescastes,
      AdministracionPublicaciones adminPublicaciones) {
    this.adminRescastes = adminRescastes;
    this.adminPublicaciones = adminPublicaciones;
  }

  public void mascotaEncontrada(MascotaEncontrada mascotaEncontrada) {
    this.mascotaEncontrada = Objects.requireNonNull(mascotaEncontrada);
  }

  /**
   * Genera un rescatista y lo registra.
   * 
   * @return el rescatista guardado.
   * @since Entrega 2
   */
  public Rescatista registrarRescate() {
    Rescatista rescatista = new Rescatista(datosPersonales(), mascotaEncontrada);
    adminRescastes.registrarRescate(rescatista);
    return rescatista;
  }

  /**
   * Completa el registro del rescate una mascota.
   * 
   * @return el rescatista.
   */
  public Rescatista completarRegistro() {
    Rescatista rescatista = registrarRescate();
    generarPublicacion();
    return rescatista;
  }

  /**
   * Realiza una publicación si la mascota no tiene chapita.
   * 
   * @since Entrega 2
   */
  private void generarPublicacion() {
    if (Objects.isNull(mascotaEncontrada.getChapita())) {
      adminPublicaciones.agregar(new Publicacion(this.mascotaEncontrada));
    }
  }

}
