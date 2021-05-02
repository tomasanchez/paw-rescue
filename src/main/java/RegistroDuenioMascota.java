import java.util.List;
import java.util.Objects;

public class RegistroDuenioMascota extends RegistroPersona{

	List<Mascota> mascotas;
	
	public void mascotas(List<Mascota> mascotas) {
		this.mascotas= mascotas;
		this.mascotas = Objects.requireNonNull(mascotas);
	}
	
	public DuenioMascota duenioMascota() {
		return new DuenioMascota(this.apellido,this.nombre,this.fechaNacimiento,this.tipoDocumento,
				this.numeroDocumento,this.contacto,this.mascotas);
	}
}
