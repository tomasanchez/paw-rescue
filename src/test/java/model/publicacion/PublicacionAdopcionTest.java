package model.publicacion;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import model.mascota.Mascota;
import model.pregunta.Pregunta;
import model.pregunta.Respuesta;
import repositories.RepoPubDarEnAdopcion;
import services.ServicioPublicacionAdopcion;

public class PublicacionAdopcionTest implements WithGlobalEntityManager {
  private RepoPubDarEnAdopcion repo;
  private ServicioPublicacionAdopcion servicioPublicacionAdopcion;
  private Mascota mascota;
  private List<Respuesta> respuestas;

  @BeforeEach
  void initPublicaciones() {
    entityManager().getTransaction().begin();
    repo = new RepoPubDarEnAdopcion();
    respuestas = new ArrayList<Respuesta>();
    mascota = new Mascota();
    servicioPublicacionAdopcion = new ServicioPublicacionAdopcion(repo);
    Respuesta respuesta = new Respuesta(new Pregunta("¿Es tranquilo?"), "si");
    Respuesta respuesta2 = new Respuesta(new Pregunta("¿Ladra mucho?"), "no");

    respuestas.add(respuesta);
    respuestas.add(respuesta2);
  }

  @AfterEach
  void endTransaction() {
    entityManager().getTransaction().rollback();
  }

  @Test
  void seCreaPublicacionParaMascotaEnAdopcionConTodasSusPreguntasRespondidas() {
    servicioPublicacionAdopcion.generarPublicacionMascotaEnAdopcion(mascota, respuestas);
    Assertions.assertFalse(repo.getEntitySet().isEmpty());
  }

  @Test
  void noSeCreaPublicacionSiNoHayRespuestas() {
    servicioPublicacionAdopcion.generarPublicacionMascotaEnAdopcion(mascota, new ArrayList<>());
    Assertions.assertTrue(repo.getEntitySet().isEmpty());
  }

}
