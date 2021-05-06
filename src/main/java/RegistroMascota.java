import java.util.List;
import java.util.Objects;

public class RegistroMascota {
  protected List<String> fotos;
  protected TipoMascota tipoMascota;
  protected String descripcionFisica;
  
  public void agregarFotos(List<String> fotos){
        this.fotos = Objects.requireNonNull(fotos);
    }
  public void  agregarDescripcion(String descripcion){
    this.descripcionFisica = Objects.requireNonNull(descripcion);
  }

    public void agregarTipoMascota(TipoMascota tipo){
        this.tipoMascota = Objects.requireNonNull(tipo);
    }
}
