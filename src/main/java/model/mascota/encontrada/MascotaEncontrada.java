package model.mascota.encontrada;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import model.mascota.Chapita;
import model.mascota.TipoMascota;
import model.mascota.caracteristica.Caracteristica;
import model.mascota.caracteristica.TamanioMascota;

public class MascotaEncontrada {

  private List<String> foto;
  private String descripcion;
  private Coordenada lugar;
  private LocalDate fecha = LocalDate.now();
  private Chapita chapita;
  private TipoMascota tipoMascota;
  private List<Caracteristica> caracteristicas;
  private TamanioMascota tamanio;

  public MascotaEncontrada setFoto(List<String> foto) {
    this.foto = foto;
    return this;
  }

  public MascotaEncontrada setDescripcion(String descripcion) {
    this.descripcion = descripcion;
    return this;
  }

  public MascotaEncontrada setLugar(Coordenada lugar) {
    this.lugar = lugar;
    return this;
  }

  public MascotaEncontrada setFecha(LocalDate fecha) {
    this.fecha = fecha;
    return this;
  }

  public MascotaEncontrada setChapita(Chapita chapita) {
    this.chapita = chapita;
    return this;
  }

  public MascotaEncontrada addFoto(String foto) {
    getFoto().add(foto);
    return this;
  }

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
  

  public MascotaEncontrada() {
    this.foto = new ArrayList<String>();
  }

  public boolean tieneChapita() {
    return Objects.isNull(getChapita());
  }

  public TipoMascota getTipoMascota() {
    return tipoMascota;
  }

  public void setTipoMascota(TipoMascota tipoMascota) {
    this.tipoMascota = tipoMascota;
  }


  public List<Caracteristica> getCaracteristicas() {
    return caracteristicas;
  }

  public void setCaracteristicas(List<Caracteristica> caracteristicas) {
    this.caracteristicas = caracteristicas;
  }
  
  /**
   * Instancia una mascota encontrada.
   *
   * @param foto la foto de la mascota
   * @param descripcion la descripcion fisica.
   * @param lugar el lugar donde fue encotnrado.
   * @param fecha la fecha en la que fue encontrado.
   * @param caracteristicas TODO
   * @param tamanio TODO
   */
  public MascotaEncontrada(List<String> foto, String descripcion, Coordenada lugar, LocalDate fecha,
      Chapita chapita, List<Caracteristica> caracteristicas, TamanioMascota tamanio) {
    this.foto = foto;
    this.descripcion = descripcion;
    this.lugar = lugar;
    this.fecha = fecha;
    this.chapita = chapita;
    this.caracteristicas=caracteristicas;
    this.tamanio=tamanio;
  }

  public TamanioMascota getTamanio() {
    return this.tamanio;
  }

 

  
}
