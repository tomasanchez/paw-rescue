import java.time.LocalDate;

/**
 * Datos de un Rescatista.
 *
 * @since 05.03.2021
 * @version 2.0
 * @author Kenti
 */
public class Rescatista {

  /**
   * Nombre, Apellido, Documento, etc.
   *
   * @since 2.0
   */
  Persona datosPeronales;

  /**
   * Animal encontrado.
   *
   * @since 1.0
   */
  MascotaEncontrada mascotaEncontrada;

  /**
   * Guarda cuenta de una persona que rescata una mascota.
   *
   * @param datosPeronales los datos de Nombre, Apellido, Documento, etc.
   * @param mascotaEncontrada el animal rescatado.
   */
  public Rescatista(Persona datosPeronales, MascotaEncontrada mascotaEncontrada) {
    this.datosPeronales = datosPeronales;
    this.mascotaEncontrada = mascotaEncontrada;
  }

  /**
   * Registra una mascota encontrada.
   * 
   * @param 
   */
  void registrarMascotaEncontrada(MascotaEncontrada mascotaEncontrada,AdministracionUsers administracionUsers) {
    this.mascotaEncontrada = mascotaEncontrada;
   administracionUsers.notificarDuenioMascotaPerdida(mascotaEncontrada);
  }
  

  public Persona getDatosPeronales() {
    return this.datosPeronales;
  }

  public MascotaEncontrada getMascotaEncontrada() {
    return this.mascotaEncontrada;
  }

  boolean compararFechaMascotaEncontrada(long dias){
    return mascotaEncontrada.fecha.isAfter(LocalDate.now().minusDays(dias));
  }
}
