import java.util.List;

public class MascotaEncontrada {

	private List <String> foto;
	private String descripcion;
	private Coordenada lugar;
	//La chapita?
  
  public String getDescripcion(){
    return this.descripcion;
  }
  
  public Coordenada getLugar(){
    return this.lugar;
  }
  
  public List <String> getFoto(){
    return this.foto;
  }
	
	public MascotaEncontrada(List<String> foto, String descripcion, Coordenada lugar) {
		this.foto = foto;
		this.descripcion = descripcion;
		this.lugar = lugar;
	}
	
	
}
