import java.util.Objects;

public class RegistroMascotaEncontrada extends RegistroMascota {
  protected Coordenada lugar;

  public void agregarUbicacion (Coordenada ubicacion){
    this.lugar = Objects.requireNonNull(ubicacion);

  }
  
  public void registrarMascotaEncontrada(RescatePatitas patitas){
    patitas.mascotasEncontradas.add(new MascotaEncontrada(this.fotos, this.descripcionFisica,this.lugar) );
  }
}
