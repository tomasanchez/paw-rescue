package model.registro;

import java.util.Objects;
import model.mascota.encontrada.MascotaEncontrada;
import model.usuario.Rescatista;

public class RegistroRescatista extends RegistroDatosPersonales {

  private MascotaEncontrada mascotaEncontrada;

  public void mascotaEncontrada(MascotaEncontrada mascotaEncontrada) {
    this.mascotaEncontrada = Objects.requireNonNull(mascotaEncontrada);
  }

  public Rescatista rescatista() {
    return new Rescatista(datosPersonales(), mascotaEncontrada);
  }

}
