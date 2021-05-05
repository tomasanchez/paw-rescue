import java.util.List;

public class Mascota {

  String nombre;
  String apodo;
  int edad;
  Sexo sexo;
  String descripcionFisica;
  List<String> fotos;
  TipoMascota tipoMascota;
  // Caracteristica a definir

  public Mascota(String nombre, String apodo, TipoMascota tipoMascota, int edad, Sexo sexo, String descripcionFisica,
      List<String> fotos) {
    this.nombre = nombre;
    this.apodo = apodo;
    this.tipoMascota = tipoMascota;
    this.edad = edad;
    this.sexo = sexo;
    this.descripcionFisica = descripcionFisica;
    this.fotos = fotos;
  }

}
