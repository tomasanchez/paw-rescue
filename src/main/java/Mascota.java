import java.util.ArrayList;
import java.util.List;
import model.mascota.caracteristica.Caracteristica;

public class Mascota {

  private final String nombre;
  private final String apodo;
  private final int edad;
  private final Sexo sexo;
  private final String descripcionFisica;
  private final List<String> fotos;
  private final TipoMascota tipoMascota;
  // Caracteristica a definir
  private final List<Caracteristica> caracteristicas;
  private final int idChapita;

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
      String descripcionFisica, List<String> fotos, int idChapita) {
    this.nombre = nombre;
    this.apodo = apodo;
    this.tipoMascota = tipoMascota;
    this.edad = edad;
    this.sexo = sexo;
    this.descripcionFisica = descripcionFisica;
    this.fotos = fotos;
    this.caracteristicas = new ArrayList<>();
    this.idChapita = idChapita;
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

  public List<Caracteristica> getCaracteristicas() {
    return this.caracteristicas;
  }

  public int getIdChapita() {
    return this.idChapita;
  }

  /**
   * Añade una nueva caracteristica.
   * 
   * @param caracteristica nueva caracteristica.
   * @return la mascota modificada.
   */
  // TODO corregir
  public Mascota agregarCaracteristica(Caracteristica caracteristica) {
    this.getCaracteristicas().add(caracteristica);
    return this;
  }

  /**
   * Busca si posee el tipo de caracteristica.
   * 
   * @param tipo la caracteristica
   * @return si posee o no la caracteristica.
   */

  // TODO corregir
  public Boolean poseeCaracteristica(TipoCaracteristica tipo) {
    return caracteristicas.stream().anyMatch(c -> c.caracteristica == tipo);
  }

  boolean esLaMismaMascota(MascotaEncontrada mascotaEncontrada) {
    return this.getIdChapita() == mascotaEncontrada.getChapita();
  }
}
