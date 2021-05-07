import java.util.ArrayList;
import java.util.List;

public class DuenioMascota extends Usuario {

  List<Mascota> mascotas;

  public DuenioMascota(Persona datosPeronales, String usuario, String password) {
    this(datosPeronales, usuario, password, new ArrayList<Mascota>());
  }

  /**
   * Mascotas registadas.
   *
   * @param datosPeronales los datos relacionados al Nombre, DNI, etc.
   * @param usuario el nombre de usuario
   * @param password password válida.
   * @param mascotas las mascotas registadas
   */
  public DuenioMascota(Persona datosPeronales, String usuario, String password,
      List<Mascota> mascotas) {
    super(datosPeronales, usuario, password);
    this.mascotas = mascotas;
  }

  /**
   * Asocia una mascota a un usuario.
   * 
   * @param mascota la mascota a registrar
   */
  public DuenioMascota registrarMascota(Mascota mascota) {
    mascotas.add(mascota);
    return this;
  }

  public List<Mascota> getMascotas() {
    return this.mascotas;
  }

}
