import java.time.LocalDate;
import java.util.List;

public class Personas {
  
  protected String apellido;
  protected String nombre;
  protected LocalDate fechaNacimiento;
  protected TipoDocumento tipoDocumento;
  protected Long numeroDocumento;
  protected Contacto contacto;


  public String getNombre(){
    return this.nombre;
  }

  public String getApellido(){
    return this.apellido;
  }

  public LocalDate getFechaNacimiento(){
    return this.fechaNacimiento;
  }

  public TipoDocumento getTipoDocumento(){
    return this.tipoDocumento;
  }

  public Long getNumeroDocumento(){
    return this.numeroDocumento;
  }

  public Contacto getContacto(){
    return this.contacto;
  }
  
}
