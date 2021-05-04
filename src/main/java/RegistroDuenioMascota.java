import java.util.List;
import java.util.Objects;

public class RegistroDuenioMascota extends RegistroPersona{

	private String usuario;
	private String password;
	List<Mascota> mascotas;
	
	public void mascotas(List<Mascota> mascotas) {
		this.mascotas= mascotas;
		this.mascotas = Objects.requireNonNull(mascotas);
	}
	
	public void usuario(String usuario) {
		this.usuario = Objects.requireNonNull(usuario);
	}
	
	public void password(String password) {
	  //TODO validar contraseña
		this.password = Objects.requireNonNull(password);
	}
	
	public DuenioMascota duenioMascota() {
		return new DuenioMascota(this.usuario, this.password,this.apellido,this.nombre,this.fechaNacimiento,this.tipoDocumento,
				this.numeroDocumento,this.contacto,this.mascotas);
	}
}
