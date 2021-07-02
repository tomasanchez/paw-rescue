package model.publicacion;

import model.mascota.Mascota;
import model.pregunta.Pregunta;
import model.pregunta.Respuesta;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.RepoAsociaciones;
import repositories.RepoPublicaciones;
import services.ServicioPublicacionAdopcion;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class PublicacionAdopcionTest {
  private RepoPublicaciones repoPublicaciones = spy(new RepoPublicaciones());
  private ServicioPublicacionAdopcion servicioPublicacionAdopcion =
      spy(new ServicioPublicacionAdopcion(repoPublicaciones));
  private Mascota mascota = mock(Mascota.class);
  private List<Respuesta> respuestas = new ArrayList<>();
  private List<Respuesta> respuestas2 = null;

  @BeforeEach
  void initPublicaciones() {
    Respuesta respuesta = new Respuesta(new Pregunta("¿Es tranquilo?"), "si");
    Respuesta respuesta2 = new Respuesta(new Pregunta("¿Ladra mucho?"), "no");
    respuestas.add(respuesta);
    respuestas.add(respuesta2);
    RepoAsociaciones.getInstance().nuevaAsociacion(mock(Asociacion.class));
  }

  @Test
  void seCreaPublicacionParaMascotaEnAdopcionConTodasSusPReguntasRespondidas() {
    servicioPublicacionAdopcion.generarPublicacionMascotaEnAdopcion(mascota, respuestas);
    Assertions.assertFalse(repoPublicaciones.getPublicacionesAdopciones().isEmpty());
  }

  @Test
  void noSeCreaPublicacionParaMascotaEnAdopcionSiNoHayRespuestas() {
    doNothing().when(servicioPublicacionAdopcion).generarPublicacionMascotaEnAdopcion(mascota,
        respuestas2);
    Assertions.assertTrue(repoPublicaciones.getPublicacionesAdopciones().isEmpty());
  }
}
