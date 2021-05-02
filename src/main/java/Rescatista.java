import java.time.LocalDate;

public class Rescatista {
	private String apellido;
	private String nombre;
	private LocalDate fechaNacimiento;
	private TipoDocumento tipoDocumento;
	private Long numeroDocumento;
	private Contacto contacto;
	private MascotaEncontrada mascotaEncontrada;
	
	public Rescatista(String apellido, String nombre, LocalDate fechaNacimiento, TipoDocumento tipoDocumento,
			Long numeroDocumento, Contacto contacto, MascotaEncontrada mascotaEncontrada) {
		super();
		this.apellido = apellido;
		this.nombre = nombre;
		this.fechaNacimiento = fechaNacimiento;
		this.tipoDocumento = tipoDocumento;
		this.numeroDocumento = numeroDocumento;
		this.contacto = contacto;
		this.mascotaEncontrada = mascotaEncontrada;
	}

	

}
