import java.util.List;
import java.util.Objects;

public class RegistroDuenioMascota extends RegistroUsuario {

  List<Mascota> mascotas;

  public RegistroUsuario mascotas(List<Mascota> mascotas) {
    this.mascotas = Objects.requireNonNull(mascotas);

    return this;
  }

  public DuenioMascota duenioMascota() {
    return new DuenioMascota(datosPeronales(), usuario, password, mascotas);
  }

}
