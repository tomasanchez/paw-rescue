import org.codehaus.jackson.annotate.JsonProperty;

public class Refugio {
  @JsonProperty("nombre")
  private String nombre;
  // Ubicacion /nuestra coordenada sin direccion
  @JsonProperty("telefono")
  private String telefono;
  @JsonProperty("capacidad")
  private int capacidad;
  @JsonProperty("lugares_disponibles")
  private int lugares_disponibles;
  
  public Refugio(){
    
  }
}
