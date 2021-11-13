package repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.persistence.NoResultException;
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

  @SuppressWarnings("unchecked")
  public List<Caracteristica> getEntitySet(List<String> ids) {
    List<Long> lids = ids.stream().map(id -> Long.valueOf(id)).collect(Collectors.toList());
    try {
      return entityManager().createQuery("FROM " + getTableName() + " C WHERE C.id IN :ids")
          .setParameter("ids", lids).getResultList();
    } catch (NoResultException e) {
      return new ArrayList<>();
    }
  }

}
