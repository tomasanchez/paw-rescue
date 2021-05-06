import Exceptions.InvalidPasswordException;

import java.time.LocalDate;
import java.util.Objects;

public abstract class RegistroPersona {
	
	protected String apellido;
	protected String nombre;
	protected LocalDate fechaNacimiento;
	protected TipoDocumento tipoDocumento;
	protected Long numeroDocumento;
	protected Contacto contacto;
 
	
	public void nombre(String nombre) {
		this.nombre = Objects.requireNonNull(nombre);
	}

	public void apellido(String apellido) {
		this.apellido = Objects.requireNonNull(nombre);
	}

	public void fechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = Objects.requireNonNull(fechaNacimiento);
	}

	public void tipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = Objects.requireNonNull(tipoDocumento);
	}

	public void numeroDocumento(Long numeroDocumento) {
		this.numeroDocumento = Objects.requireNonNull(numeroDocumento);
	}

	public void contacto(Contacto contacto) {
		this.contacto = Objects.requireNonNull(contacto);
	}
	

}
