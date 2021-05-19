import java.util.ArrayList;
import java.util.List;

public class AdministracionUsers {
  List<Administrador> administradores;
  List<DuenioMascota> dueniosMascotas;
  
   public AdministracionUsers(){
    this.dueniosMascotas = new ArrayList<>();
    this.administradores = new ArrayList<>();
  }

  public List<Administrador> getAdministradores() {
    return this.administradores;
  }

  public List<DuenioMascota> getDueniosMascotas(){ return this.dueniosMascotas; }

  public void registrarDuenioMascota(DuenioMascota duenioMascota) {
    dueniosMascotas.add(duenioMascota);
  }

  public  void registrarAdministradores(Administrador administrador){ administradores.add(administrador); }
  
  void notificarMascotaEncontrada(DuenioMascota duenioMascota){
  }

  DuenioMascota buscarDueño(MascotaEncontrada mascotaEncontrada){
    return (DuenioMascota) dueniosMascotas.stream().filter(duenio -> duenio.esDuenio(mascotaEncontrada));
  }
  
  void notificarDuenioMascotaPerdida(MascotaEncontrada mascotaEncontrada){
    notificarMascotaEncontrada(buscarDueño(mascotaEncontrada));
  }
  
}
