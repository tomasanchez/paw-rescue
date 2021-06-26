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
public class Rescate {

  /**
   * Nombre, Apellido, Documento, etc.
   *
   * @since 2.0
   */
  private DatosPersonales datosRescatista;
  
  private String domicilioRescatista;

  private MascotaEncontrada mascotaEncontrada;

  private Boolean rescatistaAlbergaMascota;

  private Refugio refugioAsignado;

  public DatosPersonales getDatosRescatista() {
    return datosRescatista;
  }

  public String getDomicilioRescatista() {
    return domicilioRescatista;
  }
  
  public MascotaEncontrada getMascotaEncontrada(){
    return this.mascotaEncontrada;
  }
  public Boolean getRescatistaAlbergaMascota() {
    return rescatistaAlbergaMascota;
  }

  public Refugio getRefugioAsignado() {
    return refugioAsignado;
  }

  public void setDatosRescatista(DatosPersonales datosRescatista) {
    this.datosRescatista = datosRescatista;
  }

  public void setDomicilioRescatista(String domicilioRescatista) {
    this.domicilioRescatista = domicilioRescatista;
  }

  public void setMascotaEncontrada(MascotaEncontrada mascotaEncontrada) {
    this.mascotaEncontrada = mascotaEncontrada;
  }

  public void setRescatistaAlbergaMascota(Boolean rescatistaAlbergaMascota) {
    this.rescatistaAlbergaMascota = rescatistaAlbergaMascota;
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
  public Rescate(DatosPersonales datosPersonales, String domicilioRescatista, MascotaEncontrada mascotaEncontrada,
      Boolean albergaMascota, Refugio refugioAsignado) {
    this.datosRescatista = datosPersonales;
    this.domicilioRescatista= domicilioRescatista;
    this.mascotaEncontrada = mascotaEncontrada;
    this.rescatistaAlbergaMascota = albergaMascota;
    this.refugioAsignado = refugioAsignado;
  }

  public Rescate() {}

 
  /**
   * Compara si la mascota fue encontrada dentro de los ultimos días.
   * 
   * @param dias los ultimos dias
   * @return si fue encontrada
   */
  public boolean estaDentroDeUltimosDias(long dias) {
    return getFechaRescate().isAfter(LocalDate.now().minusDays(dias));
  }

  public LocalDate getFechaRescate() {
    return getMascotaEncontrada().getFecha();
  }

  
  
}
