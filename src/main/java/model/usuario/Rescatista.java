package model.usuario;

import java.time.LocalDate;
import model.mascota.encontrada.MascotaEncontrada;
import model.usuario.datospersonales.DatosPersonales;
import repositories.AdministracionUsers;

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
  DatosPersonales datosPersonales;

  /**
   * Animal encontrado.
   *
   * @since 1.0
   */
  MascotaEncontrada mascotaEncontrada;

  /**
   * Guarda cuenta de una persona que rescata una mascota.
   *
   * @param datosPersonales los datos de Nombre, Apellido, Documento, etc.
   * @param mascotaEncontrada el animal rescatado.
   */
  public Rescatista(DatosPersonales datosPersonales, MascotaEncontrada mascotaEncontrada) {
    this.datosPersonales = datosPersonales;
    this.mascotaEncontrada = mascotaEncontrada;
  }


  public DatosPersonales getDatosPersonales() {
    return this.datosPersonales;
  }

  public MascotaEncontrada getMascotaEncontrada() {
    return this.mascotaEncontrada;
  }

  public boolean compararFechaMascotaEncontrada(long dias) {
    return mascotaEncontrada.getFecha().isAfter(LocalDate.now().minusDays(dias));
  }
}
