package services.mascota;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import model.mascota.Mascota;
import model.mascota.TipoMascota;
import model.preferencia.PreferenciaDeMascota;
import model.pregunta.Respuesta;
import model.publicacion.PublicacionAdoptar;
import model.publicacion.PublicacionDarEnAdopcion;
import model.usuario.DuenioMascota;
import repositories.RepoPubDarEnAdopcion;
import repositories.RepoPubParaAdoptar;
import services.usuario.contacto.ServicioNotificacion;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class RecomendadorDeAdopcionTest implements WithGlobalEntityManager {

  private RecomendadorDeAdopcion service;
  private RepoPubDarEnAdopcion repoDarAdopcion;
  private RepoPubParaAdoptar repoAdoptar;
  private List<Respuesta> rtas;
  private DuenioMascota owner;
  private ServicioNotificacion notificador;
  PublicacionAdoptar pubAdoptar;

  @BeforeEach
  void initService() {
    entityManager().getTransaction().begin();
    repoAdoptar = new RepoPubParaAdoptar();
    repoDarAdopcion = new RepoPubDarEnAdopcion();
    service = new RecomendadorDeAdopcion(repoDarAdopcion, repoAdoptar);
    rtas = new ArrayList<Respuesta>();
    owner = new DuenioMascota();
    pubAdoptar = new PublicacionAdoptar().setInteresado(owner);
    notificador = mock(ServicioNotificacion.class);
    setMock(notificador);
  }

  @AfterEach
  void endTransaction() {
    entityManager().getTransaction().rollback();
  }

  @Test
  void recomiendaPerroAlQueQuierePerro() {
    doNothing().when(notificador).notificarPosibleAdopcion(any(),any());
    PublicacionDarEnAdopcion pubEnAdopcion = publicarEnAdopcion(TipoMascota.PERRO);
    pubAdoptar.getPreferencias().add(new PreferenciaDeMascota(TipoMascota.PERRO));
    service.recomendarAdopcion(pubAdoptar);
    Assertions.assertEquals(pubEnAdopcion, owner.getRecomendacion());

  }

  @Test
  void recomiendaGatoYNoPerro() {
    doNothing().when(notificador).notificarPosibleAdopcion(any(),any());
    publicarEnAdopcion(TipoMascota.PERRO);
    publicarEnAdopcion(TipoMascota.PERRO);
    publicarEnAdopcion(TipoMascota.PERRO);
    PublicacionDarEnAdopcion gatoEnAdopcion = publicarEnAdopcion(TipoMascota.GATO);

    pubAdoptar.getPreferencias().add(new PreferenciaDeMascota(TipoMascota.GATO));
    service.recomendarAdopcion(pubAdoptar);
    Assertions.assertEquals(gatoEnAdopcion, owner.getRecomendacion());
  }

  private PublicacionDarEnAdopcion publicarEnAdopcion(TipoMascota tipo) {
    PublicacionDarEnAdopcion pub = new PublicacionDarEnAdopcion(new Mascota().setTipo(tipo), rtas);
    repoDarAdopcion.createEntity(pub);
    entityManager().flush();
    return pub;
  }

  private void setMock(ServicioNotificacion mock) {
    try {
      Field instance = ServicioNotificacion.class.getDeclaredField("instance");
      instance.setAccessible(true);
      instance.set(instance, mock);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @AfterEach
  public void resetSingleton() throws Exception {
    Field instance = ServicioNotificacion.class.getDeclaredField("instance");
    instance.setAccessible(true);
    instance.set(null, null);
  }
}
