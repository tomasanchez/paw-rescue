import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class DuenioMascota extends Personas{
  protected String usuario;
  protected String password;
	List<Mascota> mascotas;
	
	void registrarMascota(RegistroMascotaDuenio registroMascotaDuenio){
	  registroMascotaDuenio.registrarMascota(this);
  }
  void registrarMascotaEncontrada(RegistroMascotaEncontrada regisroMascotaEncontrada, RescatePatitas rescatePatitas){
	  regisroMascotaEncontrada.registrarMascotaEncontrada(rescatePatitas);
  }
  public String getUsuario(){
    return this.usuario;
  }
  public String getPassword(){
    return this.password;
  }
  
	public DuenioMascota(String usuario, String password, String apellido, String nombre, LocalDate fechaNacimiento,
			TipoDocumento tipoDocumento, Long numeroDocumento, Contacto contacto, List<Mascota> mascotas) {
		this.usuario = usuario;
		this.password = password;
		this.apellido = apellido;
		this.nombre = nombre;
		this.fechaNacimiento = fechaNacimiento;
		this.tipoDocumento = tipoDocumento;
		this.numeroDocumento = numeroDocumento;
		this.contacto = contacto;
		this.mascotas = mascotas;
	}
	
  public List<Mascota> getMascotas(){
	  return this.mascotas;
  }
  
}
