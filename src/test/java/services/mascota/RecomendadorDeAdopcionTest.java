package services.mascota;

import static org.mockito.Mockito.spy;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.mascota.Mascota;
import model.mascota.TipoMascota;
import model.preferencia.PreferenciaDeMascota;
import model.pregunta.Respuesta;
import model.publicacion.PublicacionAdoptar;
import model.publicacion.PublicacionDarEnAdopcion;
import model.usuario.DuenioMascota;
import repositories.RepoPubDarEnAdopcion;
import repositories.RepoPubParaAdoptar;

public class RecomendadorDeAdopcionTest {

  private RecomendadorDeAdopcion service;
  private RepoPubDarEnAdopcion repoDarAdopcion;
  private RepoPubParaAdoptar repoAdoptar;
  private List<Respuesta> rtas;
  private DuenioMascota owner;
  PublicacionAdoptar pubAdoptar;


  @BeforeEach
  void initService() {
    repoAdoptar = new RepoPubParaAdoptar();
    repoDarAdopcion = new RepoPubDarEnAdopcion();
    service = new RecomendadorDeAdopcion(repoDarAdopcion, repoAdoptar);
    rtas = new ArrayList<Respuesta>();
    owner = new DuenioMascota();
    pubAdoptar = new PublicacionAdoptar().setInteresado(owner);
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
    PublicacionDarEnAdopcion pub =
        spy(new PublicacionDarEnAdopcion(new Mascota().setTipo(tipo), rtas));
    repoDarAdopcion.addPublicacion(pub);
    return pub;
  }

}
