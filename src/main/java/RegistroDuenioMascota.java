import Exceptions.InvalidPasswordException;

import java.util.List;
import java.util.Objects;

public class RegistroDuenioMascota extends RegistroPersona{

  protected String usuario;
  protected String password;
	List<Mascota> mascotas;
	
	public void mascotas(List<Mascota> mascotas) {
		this.mascotas = Objects.requireNonNull(mascotas);
	}

  public void usuario(String usuario) {
    this.usuario = Objects.requireNonNull(usuario);
  }

  public void password(String password) {
    if(new ValidadorContrasenia().validarContrasenia(password))
      throw new InvalidPasswordException("La contrasenia esta en el top 10000 de las peores contraseñas");
    this.password = Objects.requireNonNull(password);
  }
	

	public void registroDuenioMascota(RescatePatitas rescatePatitas) {
    rescatePatitas.dueniosMascotas.add(new DuenioMascota(this.usuario, this.password,this.apellido,this.nombre,this.fechaNacimiento,this.tipoDocumento,
				this.numeroDocumento,this.contacto,this.mascotas));
	}
}
