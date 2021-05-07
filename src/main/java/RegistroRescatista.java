import java.util.Objects;

public class RegistroRescatista extends RegistroPersona {

  private MascotaEncontrada mascotaEncontrada;

  public void mascotaEncontrada(MascotaEncontrada mascotaEncontrada) {
    this.mascotaEncontrada = Objects.requireNonNull(mascotaEncontrada);
  }

  public Rescatista rescatista() {
    return new Rescatista(datosPeronales(), mascotaEncontrada);
  }

}

