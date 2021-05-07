import java.time.LocalDate;
import java.util.List;

public class MascotaEncontrada {

  List<String> foto;
  String descripcion;
  Coordenada lugar;
  LocalDate fecha;

  public String getDescripcion() {
    return this.descripcion;
  }

  public Coordenada getLugar() {
    return this.lugar;
  }

  public List<String> getFoto() {
    return this.foto;
  }

  /**
   * Instancia una mascota encontrada.
   * 
   * @param foto la foto de la mascota
   * @param descripcion la descripcion fisica.
   * @param lugar el lugar donde fue encotnrado.
   * @param fecha la fecha en la que fue encontrado.
   */
  public MascotaEncontrada(List<String> foto, String descripcion, Coordenada lugar,
      LocalDate fecha) {
    this.foto = foto;
    this.descripcion = descripcion;
    this.lugar = lugar;
    this.fecha = fecha;
  }

}
