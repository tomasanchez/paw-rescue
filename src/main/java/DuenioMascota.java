import java.util.ArrayList;
import java.util.List;

public class DuenioMascota extends Usuario {

  List<Mascota> mascotas;
  Persona datosPersonales;
  
  /**
   * Mascotas registadas.
   *
   * @param datosPersonales los datos relacionados al Nombre, DNI, etc.
   * @param usuario el nombre de usuario
   * @param password password válida.
   * @param mascotas las mascotas registadas
   */
  public DuenioMascota(Persona datosPersonales, String usuario, String password,
      List<Mascota> mascotas) {
    super(usuario, password);
    this.mascotas = mascotas;
    this.datosPersonales = datosPersonales;
  }
  

  /**
   * Asocia una mascota a un usuario.
   * 
   * @param mascota la mascota a registrar
   */
  public void registrarMascota(Mascota mascota) {
    mascotas.add(mascota);
  }

  boolean esDuenio(MascotaEncontrada mascotaEncontrada) {
    return mascotas.stream().anyMatch(mascota -> mascota.esLaMismaMascota(mascotaEncontrada));
  }

  public List<Mascota> getMascotas() {
    return this.mascotas;
  }

  public Integer getCantidadMascotas() {
    return mascotas.size();
  }

  public Persona getDatosPeronales() {
    return this.datosPersonales;
  }

}
