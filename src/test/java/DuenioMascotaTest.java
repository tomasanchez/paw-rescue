import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DuenioMascotaTest {

  @Test
  public void duenioMascotasTieneMasDeUnaMascota() {
    Persona persona = new Persona("Julia","Perez",new Documento(TipoDocumento.DNI,56932156),
      LocalDate.now(),contacto());
    List<Mascota> mascotas = new ArrayList<>();
    mascotas.add(perra());
    mascotas.add(gato());
    DuenioMascota duenioGatoYPerra = crearDuenio("Duenio1","16f5d96t3f",persona,mascotas);
    assertEquals(duenioGatoYPerra.getCantidadMascotas(),2);
  }

  @Test
  public void duenioMascotasNoTieneNingunaMascotas() {//El duenio de mascotas puede no tener mascotas ??
    Persona persona = new Persona("Juan","Dias",new Documento(TipoDocumento.DNI,56932786),
      LocalDate.now(),contacto());
    List<Mascota> mascotas = new ArrayList<>();
    DuenioMascota duenio = crearDuenio("Duenio2","16f589k6t3f",persona,mascotas);
    assertEquals(duenio.getCantidadMascotas(),0);
  }

  @Test
  public void duenioMascotasRegistraUnaMascotaYAhoraTiene1MascotaMas() {
    Persona persona = new Persona("Juan","Dias",new Documento(TipoDocumento.DNI,56932786),
      LocalDate.now(),contacto());
    List<Mascota> mascotas = new ArrayList<>();
    DuenioMascota duenioGato = crearDuenio("Duenio2","16f589k6t3f",persona,mascotas);
    duenioGato.registrarMascota(gato());
    assertEquals(duenioGato.getCantidadMascotas(),1);
  }
  
  
  Mascota gato(){
    return new Mascota("copito","copi",TipoMascota.GATO,6,Sexo.MACHO,
      "Tamaño grande y de pelo largo",null,165895);
  }
  
  Mascota perra(){
    return new Mascota("negra","negrita",TipoMascota.PERRO,2,Sexo.HEMBRA,
      "Pelo corto y tamaño madiano",null, 586934);
  }
  
     DuenioMascota crearDuenio(String usuario,String contraseña,Persona persona,List<Mascota> mascotas){
     RegistroUsuario registroDuenio = new RegistroUsuario();
     registroDuenio.nombre(persona.nombre);
     registroDuenio.usuario(usuario);
     registroDuenio.apellido(persona.apellido);
     registroDuenio.tipoDocumento(persona.documento.getTipo());
     registroDuenio.numeroDocumento(persona.documento.getId());
     registroDuenio.contacto(persona.getContacto());
     registroDuenio.password(contraseña);
     registroDuenio.mascota(mascotas);
     registroDuenio.fechaNacimiento(LocalDate.now());
    return registroDuenio.mascotaOwner();
  }
  
  Contacto contacto(){
    return new Contacto("Tomas","Dias",null,"tomasDias@gmail.com");
  }

    
    
    
 

  

}

