import java.util.Objects;

public class RegistroRescatista extends RegistroPersona{
	
	private MascotaEncontrada mascotaEncontrada;
	
	public void mascotaEncontrada(MascotaEncontrada mascotaEncontrada) {
		this.mascotaEncontrada = Objects.requireNonNull(mascotaEncontrada);
	}

	public Rescatista rescatista() {
		return new Rescatista(this.apellido, this.nombre, this.fechaNacimiento, this.tipoDocumento,
				this.numeroDocumento, this.contacto,this.mascotaEncontrada);
	}

}

