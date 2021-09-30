package services.mascota;

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

public class RecomendadorDeAdopcionTest implements WithGlobalEntityManager {

  private RecomendadorDeAdopcion service;
  private RepoPubDarEnAdopcion repoDarAdopcion;
  private RepoPubParaAdoptar repoAdoptar;
  private List<Respuesta> rtas;
  private DuenioMascota owner;
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
  }

  @AfterEach
  void endTransaction() {
    entityManager().getTransaction().rollback();
  }

  @Test
  void recomiendaPerroAlQueQuierePerro() {

    PublicacionDarEnAdopcion pubEnAdopcion = publicarEnAdopcion(TipoMascota.PERRO);
    pubAdoptar.getPreferencias().add(new PreferenciaDeMascota(TipoMascota.PERRO));
    service.recomendarAdopcion(pubAdoptar);
    Assertions.assertEquals(pubEnAdopcion, owner.getRecomendacion());

  }

  @Test
  void recomiendaGatoYNoPerro() {

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

}
