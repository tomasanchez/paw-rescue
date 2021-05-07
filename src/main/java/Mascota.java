import java.util.List;

public class Mascota {

  private String nombre;
  private String apodo;
  private int edad;
  private Sexo sexo;
  private String descripcionFisica;
  private List<String> fotos;
  private TipoMascota tipoMascota;
  // Caracteristica a definir

  /**
   * Instancia una mascota.
   * 
   * @param nombre el nombre de la mascota.
   * @param apodo un apodo.
   * @param tipoMascota el tipo de animal.
   * @param edad su edad en años.
   * @param sexo el sexo del animal.
   * @param descripcionFisica una descripción física.
   * @param fotos Lista de URLs de fotos.
   */
  public Mascota(String nombre, String apodo, TipoMascota tipoMascota, int edad, Sexo sexo,
      String descripcionFisica, List<String> fotos) {
    this.nombre = nombre;
    this.apodo = apodo;
    this.tipoMascota = tipoMascota;
    this.edad = edad;
    this.sexo = sexo;
    this.descripcionFisica = descripcionFisica;
    this.fotos = fotos;
  }

  public String getNombre() {
    return this.nombre;
  }

  public String getApodo() {
    return this.apodo;
  }

  public String getDescripcionFisica() {
    return this.descripcionFisica;
  }

  public TipoMascota getTipoMascota() {
    return this.tipoMascota;
  }

  public Sexo getSexo() {
    return this.sexo;
  }

  public int getEdad() {
    return this.edad;
  }

  public List<String> getFotos() {
    return this.fotos;
  }

}
