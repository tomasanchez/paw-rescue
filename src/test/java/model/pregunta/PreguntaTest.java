package model.pregunta;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PreguntaTest {

  @Test
  void PreguntaOpcionesAgregar() {
    PreguntaOpciones preguntaOpciones = new PreguntaOpciones("Cuantas veces sale a pasear");
    preguntaOpciones.agregarOpcion(new Respuesta("1 vez al dia"));
    preguntaOpciones.agregarOpcion(new Respuesta("2 veces al dia"));
    preguntaOpciones.agregarOpcion(new Respuesta("3 veces al dia"));
    preguntaOpciones.agregarOpcion(new Respuesta("4 o mas veces al dia"));
    Assertions.assertEquals(4, preguntaOpciones.getPosiblesRespuestas().size());
  }

  @Test
  void PreguntaOpcionesQuitar() {
    PreguntaOpciones preguntaOpciones = new PreguntaOpciones("Cuantas veces sale a pasear");
    Respuesta rta = new Respuesta("2 veces al dia");

    preguntaOpciones.agregarOpcion(new Respuesta("1 vez al dia"));
    preguntaOpciones.agregarOpcion(rta);
    preguntaOpciones.quitarOpcion(rta);

    Assertions.assertEquals(1, preguntaOpciones.getPosiblesRespuestas().size());
  }

  @Test
  void PreguntaVOFdosOpciones() {
    PreguntaVOF preguntaVOF = new PreguntaVOF("Es cariñoso");
    Assertions.assertEquals(2, preguntaVOF.posiblesRespuestas.size());
  }
}
