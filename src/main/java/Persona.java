import java.util.ArrayList;
import java.util.List;

/*abstract class Usuario{
  abstract  void registrarMascota(Mascota nuevaMascota);
  abstract void cargarMascotaPerdida(MascotaPerdida mascotaPerdida);
  abstract void agregarCaracteristicasDeMascota(Mascota mascota, Caracteristica caracteristica);
  abstract List<MascotaPerdida> ListarUltimasMascotasPerdidas();
}

abstract class Persona extends Usuario{
  String nombreYApellido;
  int fecaNacimiento;
  int numeroDocumento;
  TipoDocumento tipoDocumento;
  DatoContacto datoContacto;
  List<Mascota> mascotas = new ArrayList<>();

  @Override
  void registrarMascota(Mascota nuevaMascota) {
    mascotas.add(nuevaMascota);
  }
  
  void informarMascotaPerdida(MascotaPerdida mascotaPerdida,Administrador admin){
    admin.cargarMascotaPerdida(mascotaPerdida);
  }

}
  
  class Rescatista {
    
  }
  

enum TipoDocumento{
  DNI,CI,LC,LE
}
class DatoContacto{
  String nombreYApellido;
  int telefono;
  String mail;
}

class Mascota extends Usuario{
  String nombre;
  String apodo;
  TipoAnimal tipoAnimal;
  int edad;
  Sexo sexo;
  String descripcionFisica;
  String foto;
  Caracteristica caracteristicas;
  
}
enum Sexo{
  MASCULINO,FEMENINO
}

enum Caracteristica{ //Modificar
  CASTRADO,COLORSECUNDARIO,COLORPRINCIPAL
}

enum TipoAnimal{
  PERRO,GATO
}
class MascotaPerdida{
  String foto;
  //Estado estado;
  //LugarEncuentro lugarEncuetro;
  int fechaEncontrado;
}

abstract class Administrador {
  List<MascotaPerdida> mascotasPerdidas = new ArrayList<>();
  List<Persona> dueñosDeMascotas = new ArrayList<>();

  @Override
  void cargarMascotaPerdida(MascotaPerdida mascotaPerdida) {
    mascotasPerdidas.add(mascotaPerdida);
  }
  
}
*/
