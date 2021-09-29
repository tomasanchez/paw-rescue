package model.mascota;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.mascota.encontrada.MascotaEncontrada;
import model.usuario.DuenioMascota;

public class MascotaEncontradaTest {

  private MascotaEncontrada mascota;
  private DuenioMascota duenio;
  private Chapita chapita;

  @BeforeEach
  void initMascota() {
    this.mascota = new MascotaEncontrada();
    this.duenio = new DuenioMascota();
    this.chapita = new Chapita(duenio);
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
