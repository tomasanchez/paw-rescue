package model.mascota;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import model.mascota.encontrada.MascotaEncontrada;
import model.usuario.DuenioMascota;

public class MascotaEncontradaTest implements WithGlobalEntityManager{

  private MascotaEncontrada mascota;
  private DuenioMascota duenio;
  private Chapita chapita;

  @BeforeEach
  void initMascota() {
    entityManager().getTransaction().begin();
    this.mascota = new MascotaEncontrada();
    this.duenio = new DuenioMascota();
    this.chapita = new Chapita(duenio);
  }
  
  @AfterEach
  void endTransaction() {
    entityManager().getTransaction().rollback();
  }

  
  @Test
  void mascotaSinChapita() {
    Assertions.assertEquals(mascota.getChapita(), null);
  }

  @Test
  void mascotaConChapitaMatcheaConPerdida() {
    mascota.setChapita(chapita);
    Mascota perdida = new Mascota();
    perdida.setChapita(chapita);
    Assertions.assertTrue(perdida.esLaMismaMascota(mascota));
  }

  
  
}
