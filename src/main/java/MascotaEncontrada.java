import java.time.LocalDate;
import java.util.List;

public class MascotaEncontrada {

  List<String> foto;
  String descripcion;
  Coordenada lugar;
  LocalDate fecha;

  public MascotaEncontrada(List<String> foto, String descripcion, Coordenada lugar, LocalDate fecha) {
    this.foto = foto;
    this.descripcion = descripcion;
    this.lugar = lugar;
    this.fecha = fecha;
  }

}
