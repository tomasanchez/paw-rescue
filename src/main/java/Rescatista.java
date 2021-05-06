import java.time.LocalDate;

public class Rescatista extends Personas {
  
	public Rescatista(String apellido, String nombre, LocalDate fechaNacimiento, TipoDocumento tipoDocumento,
			Long numeroDocumento, Contacto contacto) {
		this.apellido = apellido;
		this.nombre = nombre;
		this.fechaNacimiento = fechaNacimiento;
		this.tipoDocumento = tipoDocumento;
		this.numeroDocumento = numeroDocumento;
		this.contacto = contacto;
	}

 
}
