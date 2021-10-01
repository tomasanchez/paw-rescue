package model.asociacion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import model.mascota.encontrada.Coordenada;
import model.pregunta.Pregunta;
import model.pregunta.PreguntaVOF;
import model.publicacion.Asociacion;
import repositories.RepoAsociaciones;
import repositories.RepoPreguntas;

public class AsociacionTest implements WithGlobalEntityManager {
  RepoPreguntas repoPreguntas;
  RepoAsociaciones repoAsociaciones;

  @BeforeEach
  void init() {
    entityManager().getTransaction().begin();
    repoPreguntas = new RepoPreguntas();
    repoAsociaciones = new RepoAsociaciones();
  }

  @AfterEach
  void endTransaction() {
    entityManager().getTransaction().rollback();
  }

  @Test
  void PreguntasGenericasYPreguntasAsociacion() {
    Pregunta pregunta = new PreguntaVOF("Necesita patio");
    List<Pregunta> preguntasAsociacion = new ArrayList<>();
    preguntasAsociacion.add(pregunta);
    Asociacion asociacion = new Asociacion(new Coordenada("-10", "10"), "Calle falsa 123", preguntasAsociacion);
    repoPreguntas.createEntity(new Pregunta("id chapita"));
    Assertions.assertEquals(2, asociacion.getPreguntas().size());

  }

  @Test
  void QuitarPreguntas() {
    Pregunta pregunta = new PreguntaVOF("Necesita patio");
    List<Pregunta> preguntasAsociacion = new ArrayList<>();
    preguntasAsociacion.add(pregunta);
    Asociacion asociacion = new Asociacion(new Coordenada("-10", "10"), "Calle falsa 123", preguntasAsociacion);
    repoPreguntas.createEntity(new Pregunta("id chapita"));
    Assertions.assertEquals(2, asociacion.getPreguntas().size());
    repoPreguntas.deleteEntity(pregunta);
    Assertions.assertEquals(1, repoPreguntas.getEntitySet().size());
  }

  @Test
  void sePersisteUnaAsociacion() {
    Pregunta pregunta = new PreguntaVOF("Necesita patio");
    List<Pregunta> preguntasAsociacion = new ArrayList<>();
    preguntasAsociacion.add(pregunta);
    Asociacion asociacion = new Asociacion(new Coordenada("-10", "10"), "Calle falsa 123", preguntasAsociacion);
    repoAsociaciones.createEntity(asociacion);
    assertSame(asociacion, entityManager().find(Asociacion.class, asociacion.getId()));
  }

  @Test
  void seRecuperaUnUsuario() {
    Pregunta pregunta = new PreguntaVOF("Necesita patio");
    List<Pregunta> preguntasAsociacion = new ArrayList<>();
    preguntasAsociacion.add(pregunta);
    Asociacion asociacion = new Asociacion(new Coordenada("-10", "10"), "Calle falsa 123", preguntasAsociacion);
    pregunta.getPosiblesRespuestas().forEach(cada -> {
      entityManager().persist(cada);
    });
    repoPreguntas.createEntity(pregunta);
    repoAsociaciones.createEntity(asociacion);
    entityManager().flush();
    assertEquals(asociacion.getId(), repoAsociaciones.getEntity(asociacion.getId()).getId());
  }

  @Test
  void seRecuperaNULLSiNoExisteUsuario() {
    assertNull(repoAsociaciones.getEntity(-1));
  }

  @Test
  void seEliminaUnUsuario() {
    Pregunta pregunta = new PreguntaVOF("Necesita patio");
    List<Pregunta> preguntasAsociacion = new ArrayList<>();
    preguntasAsociacion.add(pregunta);
    Asociacion asociacion = new Asociacion(new Coordenada("-10", "10"), "Calle falsa 123", preguntasAsociacion);
    pregunta.getPosiblesRespuestas().forEach(cada -> {
      entityManager().persist(cada);
    });
    repoPreguntas.createEntity(pregunta);
    repoAsociaciones.createEntity(asociacion);
    entityManager().flush();
    repoAsociaciones.deleteEntity(asociacion);
    entityManager().flush();
    assertEquals(0, repoAsociaciones.getEntitySet().size());
  }

}
