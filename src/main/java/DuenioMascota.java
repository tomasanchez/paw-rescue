import java.util.List;

public class DuenioMascota extends Usuario{
  List<Mascota> mascotas;

  /**
   * Mascotas registadas
   *
   * @param datosPeronales
   * @param usuario
   * @param password
   */
  public DuenioMascota(Persona datosPeronales, String usuario, String password,List<Mascota> mascotas) {
    super(datosPeronales, usuario, password);
    this.mascotas = mascotas;
  }


  void registrarMascota(Mascota mascota){
    mascotas.add(mascota);
  }
  public  List<Mascota> getMascotas(){
    return this.mascotas;
  }
  
  
}
