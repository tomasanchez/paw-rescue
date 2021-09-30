package model.asociacion;

import model.mascota.encontrada.Coordenada;
import model.pregunta.Pregunta;
import model.pregunta.PreguntaVOF;
import model.publicacion.Asociacion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.RepoPreguntas;

import java.util.ArrayList;
import java.util.List;

public class AsociacionTest {
  RepoPreguntas repoPreguntas;

  @BeforeEach
  void init() {
    repoPreguntas = RepoPreguntas.getInstance();
    Pregunta pregunta1 = new Pregunta("id chapita");
    Pregunta pregunta2 = new Pregunta("Porque motivo desea darlo en adopcion");
    repoPreguntas.agregarPregunta(pregunta1);
    repoPreguntas.agregarPregunta(pregunta2);
    if(repoPreguntas.getPreguntas().size() != 2){
      repoPreguntas.quitarPregunta(pregunta1);
      repoPreguntas.quitarPregunta(pregunta2);
    }
  }

  @Test
  void PreguntasGenericasYPreguntasAsociacion() {
    Pregunta pregunta = new PreguntaVOF("Necesita patio");
    List<Pregunta> preguntasAsociacion = new ArrayList<>();
    preguntasAsociacion.add(pregunta);
    Asociacion asociacion = new Asociacion(new Coordenada("-10", "10"), "Calle falsa 123",  preguntasAsociacion);
    Assertions.assertEquals(3, asociacion.getPreguntas().size());

  }

  @Test
  void QuitarPreguntas() {
    Pregunta pregunta = new PreguntaVOF("Necesita patio");
    List<Pregunta> preguntasAsociacion = new ArrayList<>();
    preguntasAsociacion.add(pregunta);
    Asociacion asociacion = new Asociacion(new Coordenada("-10", "10"), "Calle falsa 123",  preguntasAsociacion);
    Assertions.assertEquals(3, asociacion.getPreguntas().size());
    asociacion.quitarPregunta(pregunta);
    Assertions.assertEquals(2, asociacion.getPreguntas().size());
  }
}
