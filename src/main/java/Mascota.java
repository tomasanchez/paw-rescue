import java.util.List;

public class Mascota {

	private String nombre;
	private String apodo;
	private int edad;
	private Sexo sexo;
	private String descripcionFisica;
	private List<String> fotos;
	private TipoMascota tipoMascota;
	List<Caracteristica> caracteristicas; 
  
  public String getNombre(){
    return this.nombre;
  }
  
  public String getApodo(){
    return this.apodo;
  }
  
  public String getDescripcionFisica(){
    return this.descripcionFisica;
  }
  
  public int getEdad(){
    return this.edad;
  }
  
  public Sexo getSexo(){
    return this.sexo;
  }
  
  public List<String> getFotos(){
    return this.fotos;
  }
  
  public TipoMascota getTipoMascota(){
    return this.tipoMascota;
  }
  
  public List<Caracteristica> getCaracteristicas(){
    return this.caracteristicas;
  }

	public Mascota(String nombre, String apodo,TipoMascota tipoMascota, int edad, Sexo sexo, String descripcionFisica, 
			List<String> fotos) {
		this.nombre = nombre;
		this.apodo = apodo;
		this.tipoMascota = tipoMascota;
		this.edad = edad;
		this.sexo = sexo;
		this.descripcionFisica = descripcionFisica;
		this.fotos = fotos;
	}

}
