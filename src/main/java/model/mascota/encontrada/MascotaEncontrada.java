package model.mascota.encontrada;

import java.time.LocalDate;
import java.util.List;
import model.mascota.Chapita;

public class MascotaEncontrada {

  private List<String> foto;
  private String descripcion;
  private Coordenada lugar;
  private LocalDate fecha;
  private Chapita chapita;

  public String getDescripcion() {
    return this.descripcion;
  }

  public Coordenada getLugar() {
    return this.lugar;
  }

  public Chapita getChapita() {
    return this.chapita;
  }

  public List<String> getFoto() {
    return this.foto;
  }

  public LocalDate getFecha() {
    return this.fecha;
  }

  /**
   * Instancia una mascota encontrada.
   * 
   * @param foto la foto de la mascota
   * @param descripcion la descripcion fisica.
   * @param lugar el lugar donde fue encotnrado.
   * @param fecha la fecha en la que fue encontrado.
   */
  public MascotaEncontrada(List<String> foto, String descripcion, Coordenada lugar, LocalDate fecha,
      Chapita chapita) {
    this.foto = foto;
    this.descripcion = descripcion;
    this.lugar = lugar;
    this.fecha = fecha;
    this.chapita = chapita;
  }

}
