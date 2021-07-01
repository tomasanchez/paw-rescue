package model.pregunta;

import exceptions.RespuestaInvalida;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class RespuestaTest {
  @Test
  void RespuestaVerdadera() {
    Pregunta pregunta = nuevaPreguntaVOF("Es cariñoso");
    Respuesta respuesta = nuevaRespuesta(pregunta, "Verdadero");
    Assertions.assertEquals("Verdadero", respuesta.getRespuesta());
  }

  @Test
  void PreguntaVOFRespuestaBananaException() {
    Pregunta pregunta = nuevaPreguntaVOF("Es cariñoso");
    assertThrows(RespuestaInvalida.class, () -> nuevaRespuesta(pregunta, "Banana"));
  }

  @Test
  void PreguntaChoiceRespuestaCorrecta() {
    PreguntaOpciones pregunta = nuevaPreguntaOpciones("Cuantas veces pasea al dia");
    pregunta.agregarOpcion("1 vez");
    pregunta.agregarOpcion("2 veces");
    pregunta.agregarOpcion("3 veces");
    Respuesta respuesta = nuevaRespuesta(pregunta, "2 veces");
    Assertions.assertEquals(respuesta.getRespuesta(), "2 veces");
  }

  @Test
  void PreguntaChoiceRespuestaInvalida() {
    PreguntaOpciones pregunta = nuevaPreguntaOpciones("Tiene apodo");
    pregunta.agregarOpcion("Pancho");
    pregunta.agregarOpcion("Pepe");
    assertThrows(RespuestaInvalida.class, () -> nuevaRespuesta(pregunta, "Michi"));
  }

  @Test
  void PreguntaLibre() {
    Pregunta pregunta = nuevaPreguntaLibre("Tiene apodo");
    Respuesta respuesta1 = nuevaRespuesta(pregunta, "Michi");
    Assertions.assertEquals("Michi", respuesta1.getRespuesta());
    Respuesta respuesta2 = nuevaRespuesta(pregunta, "gordo");
    Assertions.assertEquals("gordo", respuesta2.getRespuesta());
  }

  @Test
  void RespestaVaciaEnVOF() {
    Pregunta pregunta = nuevaPreguntaVOF("Es cariñoso");
    assertThrows(RespuestaInvalida.class, () -> nuevaRespuesta(pregunta, ""));
    assertThrows(RespuestaInvalida.class, () -> nuevaRespuesta(pregunta, null));
  }

  private PreguntaVOF nuevaPreguntaVOF(String encuesta) {
    return new PreguntaVOF(encuesta);
  }

  private Pregunta nuevaPreguntaLibre(String encuesta) {
    return new Pregunta(encuesta);
  }

  private PreguntaOpciones nuevaPreguntaOpciones(String encuesta) {
    return new PreguntaOpciones(encuesta);
  }

  private Respuesta nuevaRespuesta(Pregunta pregunta, String respuesta) {
    return new Respuesta(pregunta, respuesta);
  }
}
