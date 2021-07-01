package model.pregunta;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class PreguntaTest {
  @Test
  void PreguntaOpcionesAgregar() {
    PreguntaOpciones preguntaOpciones = new PreguntaOpciones("Cuantas veces sale a pasear");
    preguntaOpciones.agregarOpcion("1 vez al dia");
    preguntaOpciones.agregarOpcion("2 veces al dia");
    preguntaOpciones.agregarOpcion("3 veces al dia");
    preguntaOpciones.agregarOpcion("4 o mas veces al dia");
    Assertions.assertEquals(4, preguntaOpciones.getPosiblesRespuestas().size());
  }
  
  @Test
  void PreguntaOpcionesQuitar() {
    PreguntaOpciones preguntaOpciones = new PreguntaOpciones("Cuantas veces sale a pasear");
    preguntaOpciones.agregarOpcion("1 vez al dia");
    preguntaOpciones.agregarOpcion("2 veces al dia");
    preguntaOpciones.quitarOpcion("2 veces al dia");
    Assertions.assertEquals(1, preguntaOpciones.getPosiblesRespuestas().size());
  }

  @Test
  void PreguntaVOFdosOpciones(){
    PreguntaVOF preguntaVOF = new PreguntaVOF("Es cariñoso");
    Assertions.assertEquals(2,preguntaVOF.posiblesRespuestas.size());
    Assertions.assertTrue(preguntaVOF.getPosiblesRespuestas().contains("Verdadero"));
    Assertions.assertTrue(preguntaVOF.getPosiblesRespuestas().contains("Falso"));
  }
}
