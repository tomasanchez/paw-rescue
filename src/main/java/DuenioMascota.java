import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class DuenioMascota {

	private String usuario;
	private String password;
	private String apellido;
	private String nombre;
	private LocalDate fechaNacimiento;
	private TipoDocumento tipoDocumento;
	private Long numeroDocumento;
	private Contacto contacto;
	List<Mascota> mascotas;

	public DuenioMascota(String usuario, String password, String apellido, String nombre, LocalDate fechaNacimiento,
			TipoDocumento tipoDocumento, Long numeroDocumento, Contacto contacto, List<Mascota> mascotas) {
		super();
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

}
