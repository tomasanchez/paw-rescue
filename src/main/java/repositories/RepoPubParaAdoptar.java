package repositories;

import java.util.ArrayList;
import java.util.List;
import model.publicacion.PublicacionAdoptar;

public class RepoPubParaAdoptar {

  private List<PublicacionAdoptar> publicaciones = new ArrayList<PublicacionAdoptar>();

  public List<PublicacionAdoptar> getInteresados() {
    return publicaciones;
  }

  public void setInteresados(List<PublicacionAdoptar> interesados) {
    this.publicaciones = interesados;
  }

  public void addPublicacion(PublicacionAdoptar pub) {
    getInteresados().add(pub);
  }
}
