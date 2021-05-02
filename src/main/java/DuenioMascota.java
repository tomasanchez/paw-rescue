import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class DuenioMascota {

	private String apellido;
	private String nombre;
	private LocalDate fechaNacimiento;
	private TipoDocumento tipoDocumento;
	private Long numeroDocumento;
	private Contacto contacto;
	List<Mascota> mascotas;

	public DuenioMascota(String apellido, String nombre, LocalDate fechaNacimiento, TipoDocumento tipoDocumento,
			Long numeroDocumento, Contacto contacto, List<Mascota> mascotas) {
		this.apellido = apellido;
		this.nombre = nombre;
		this.fechaNacimiento = fechaNacimiento;
		this.tipoDocumento = tipoDocumento;
		this.numeroDocumento = numeroDocumento;
		this.contacto = contacto;
		this.mascotas = mascotas;
	}

}
