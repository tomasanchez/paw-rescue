import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

public class MascotaEncontrada {

  List<String> foto;
  String descripcion;
  Coordenada lugar;
  LocalDate fecha;
  int chapita;

  public String getDescripcion() {
    return this.descripcion;
  }

  public Coordenada getLugar() {
    return this.lugar;
  }
  
  public int getChapita(){ return this.chapita; }

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
      LocalDate fecha, int chapita) {
    this.foto = foto;
    this.descripcion = descripcion;
    this.lugar = lugar;
    this.fecha = fecha;
    this.chapita = chapita;
  }

 
  
}
