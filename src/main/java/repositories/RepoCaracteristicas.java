package repositories;

import java.util.Objects;
import db.PersistentEntitySet;
import model.mascota.caracteristica.Caracteristica;

public class RepoCaracteristicas extends PersistentEntitySet<Caracteristica> {

  private static RepoCaracteristicas instance;

  public static RepoCaracteristicas getInstance() {

    if (Objects.isNull(instance)) {
      instance = new RepoCaracteristicas();
    }

    return instance;
  }


}
