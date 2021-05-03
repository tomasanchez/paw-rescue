import java.util.List;

public class Mascota {

	private String nombre;
	private String apodo;
	private int edad;
	private Sexo sexo;
	private String descripcionFisica;
	private List<String> fotos;
	private TipoMascota tipoMascota;
	// Caracteristica a definir

	public Mascota(String nombre, String apodo,TipoMascota tipoMascota, int edad, Sexo sexo, String descripcionFisica, 
			List<String> fotos) {
		super();
		this.nombre = nombre;
		this.apodo = apodo;
		this.tipoMascota = tipoMascota;
		this.edad = edad;
		this.sexo = sexo;
		this.descripcionFisica = descripcionFisica;
		this.fotos = fotos;
	}

}
