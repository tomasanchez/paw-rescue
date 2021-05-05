import java.util.List;

public class MascotaEncontrada {

  List<String> foto;
  String descripcion;
  Coordenada lugar;

  public MascotaEncontrada(List<String> foto, String descripcion, Coordenada lugar) {
    this.foto = foto;
    this.descripcion = descripcion;
    this.lugar = lugar;
  }

}
