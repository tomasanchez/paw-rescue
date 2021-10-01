package repositories;

import java.util.List;
import java.util.stream.Collectors;

import db.PersistentEntitySet;
import model.publicacion.PublicacionRescate;

public class RepoPubRescate extends PersistentEntitySet<PublicacionRescate> {

  public List<PublicacionRescate> getPublicacionesInactivas() {
    return getEntitySet().stream().filter(PublicacionRescate::isActiva).collect(Collectors.toList());
  }

}
