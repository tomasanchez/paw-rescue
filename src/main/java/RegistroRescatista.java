


public class RegistroRescatista extends RegistroPersona{
  
	public Rescatista rescatista() {
		return new Rescatista(this.apellido, this.nombre, this.fechaNacimiento, this.tipoDocumento,
				this.numeroDocumento, this.contacto);
	}

}

