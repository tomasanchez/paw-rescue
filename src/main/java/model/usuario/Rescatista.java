package model.usuario;

import java.time.LocalDate;
import model.mascota.encontrada.MascotaEncontrada;
import model.refugio.Refugio;
import model.usuario.datospersonales.DatosPersonales;

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

  Boolean albergarMascota;

  String direccion;

  Refugio refugioAsignado;

  public Boolean getAlbergarMascota() {
    return albergarMascota;
  }

  public String getDireccion() {
    return direccion;
  }

  public Refugio getRefugioAsignado() {
    return refugioAsignado;
  }

  public void setAlbergarMascota(Boolean albergarMascota) {
    this.albergarMascota = albergarMascota;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  public void setRefugioAsignado(Refugio refugioAsignado) {
    this.refugioAsignado = refugioAsignado;
  }

  /**
   * Guarda cuenta de una persona que rescata una mascota.
   *
   * @param datosPersonales los datos de Nombre, Apellido, Documento, etc.
   * @param mascotaEncontrada el animal rescatado.
   * @param direccion la direccion donde fue encontrado
   * @param refugioAsignado el refugio al que se asigno la mascota
   */
  public Rescatista(DatosPersonales datosPersonales, MascotaEncontrada mascotaEncontrada,
      Boolean albergarMascota, String direccion, Refugio refugioAsignado) {
    this.datosPersonales = datosPersonales;
    this.mascotaEncontrada = mascotaEncontrada;
    this.albergarMascota = albergarMascota;
    this.direccion = direccion;
    this.refugioAsignado = refugioAsignado;
  }

  public boolean puedeAlbergarMascota() {
    return this.albergarMascota;
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
