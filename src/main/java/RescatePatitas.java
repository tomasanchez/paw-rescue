import java.util.List;

public class RescatePatitas  {
  List<MascotaEncontrada> mascotasEncontradas;
  List<DuenioMascota> dueniosMascotas;
  
  public List<MascotaEncontrada> getMascotasEncontradas(){
    return  this.mascotasEncontradas;
  }
  
  public List<DuenioMascota> getDueniosMascotas(){
    return this.dueniosMascotas;
  }
  
  public void crearUsuarioDuenio(RegistroDuenioMascota registroDuenioMascota){
    registroDuenioMascota.registroDuenioMascota(this);
  }
}
