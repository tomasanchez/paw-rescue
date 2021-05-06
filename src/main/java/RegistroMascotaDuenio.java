import java.util.List;
import java.util.Objects;

public class RegistroMascotaDuenio extends RegistroMascota{
  protected String nombre;
  protected String apodo;
  protected int edad;
  protected Sexo sexo;
  protected List<Caracteristica> caracteristicas;

  public void agregarNombreYApodo(String nombre,String apodo){
      this.nombre = Objects.requireNonNull(nombre);
      this.apodo= Objects.requireNonNull(apodo);
  }

  public void   agregarEdad(int edad){
      this.edad = Objects.requireNonNull(edad);
  }

  public void   agregarSexo(Sexo sexo){ 
    this.sexo = Objects.requireNonNull(sexo);
  }
  
  
  public void registrarMascota(DuenioMascota duenio) { //faltan las caracteristicas
    duenio.mascotas.add(new Mascota(this.nombre, this.apodo,this.tipoMascota,this.edad,this.sexo,this.descripcionFisica,
      this.fotos));
  }
}
