package repositories;

import java.util.ArrayList;
import java.util.List;

public class RepoPubParaAdoptar {

  private List<Object> publicaciones = new ArrayList<Object>();

  public List<Object> getInteresados() {
    return publicaciones;
  }

  public void setInteresados(List<Object> interesados) {
    this.publicaciones = interesados;
  }

}
