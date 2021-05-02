import java.util.List;

public class Mascota {

	private String nombre;
	private String apodo;
	private int edad;
	private Sexo sexo;
	private String descripcionFisica;
	private List<String> fotos;
	// Caracteristica a definir

	public Mascota(String nombre, String apodo, int edad, Sexo sexo, String descripcionFisica, 
			List<String> fotos) {
		super();
		this.nombre = nombre;
		this.apodo = apodo;
		this.edad = edad;
		this.sexo = sexo;
		this.descripcionFisica = descripcionFisica;
		this.fotos = fotos;
	}

}
