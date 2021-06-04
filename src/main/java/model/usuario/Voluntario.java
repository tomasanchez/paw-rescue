package model.usuario;

import java.util.ArrayList;
import java.util.List;
import model.publicacion.Publicacion;

public class Voluntario extends Usuario {

  List<Publicacion> publicacionesGestionadas = new ArrayList<Publicacion>();

  public List<Publicacion> getPublicacionesGestionadas() {
    return publicacionesGestionadas;
  }

  public Voluntario addPublicacion(Publicacion publicacion) {
    getPublicacionesGestionadas().add(publicacion);
    return this;
  }

  public Voluntario setPublicacionesGestionadas(List<Publicacion> publicacionesGestionadas) {
    this.publicacionesGestionadas = publicacionesGestionadas;
    return this;
  }

}
