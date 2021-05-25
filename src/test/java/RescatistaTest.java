import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RescatistaTest {
  
  /*@Test
  public void SeInformaUnaMascotaEncontradaPorUnaPersona() {//mofificar
    Persona persona = new Persona("Lucia","Gomez",new Documento(TipoDocumento.DNI,15632659),
      LocalDate.now(),contacto());
    Rescatista rescatistaGato = crearRescatista(persona, gatoEncontrado());
    AdministracionUsers administracionUsers = new AdministracionUsers();
    rescatistaGato.registrarMascotaEncontrada(gatoEncontrado(), administracionUsers);
    //assertEquals(administracionUsers.,1);
  }*/
  
  MascotaEncontrada gatoEncontrado(){
    List<String> fotos = new ArrayList<>();
    fotos.add("foto1");
    return new MascotaEncontrada(fotos,"Tamaño grande y de pelo corto",new Coordenada("df52","54gn"),
      LocalDate.now(),4523);
  }

  MascotaEncontrada perroEncontrado(){
    List<String> fotos = new ArrayList<>();
    fotos.add("foto1");
    return new MascotaEncontrada(fotos,"Tamaño chico y pelo corto",new Coordenada("jf72","89pn"),
      LocalDate.now(),8965);
  }
  
     Rescatista crearRescatista(Persona persona,MascotaEncontrada mascota){
    
     RegistroRescatista registroRescatista = new RegistroRescatista();
     registroRescatista.nombre(persona.nombre);
     registroRescatista.apellido(persona.apellido);
     registroRescatista.tipoDocumento(persona.documento.getTipo());
     registroRescatista.numeroDocumento(persona.documento.getId());
     registroRescatista.contacto(persona.getContacto());
     registroRescatista.mascotaEncontrada(mascota);
     registroRescatista.fechaNacimiento(LocalDate.now());
     
    return registroRescatista.rescatista();
  }


  Contacto contacto(){
    return new Contacto("Tomas","Dias",null,"tomasDias@gmail.com");
  }
    
 

  

}

