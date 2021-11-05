package repositories;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import model.mascota.caracteristica.Caracteristica;

public class RepoCaracteristicasTest implements WithGlobalEntityManager, TransactionalOps {


  private RepoCaracteristicas repo;

  @BeforeEach
  void initRepo() {
    repo = new RepoCaracteristicas();
  }

  @Test
  void sePersisteUnaCaracteristicaSiNoExiste() {

    List<Caracteristica> caracteristicas = repo.getEntitySet();

    if (caracteristicas.isEmpty()) {
      withTransaction(() -> {
        repo.createEntity(new Caracteristica("Una caracteristica de prueba"));
      });
    }

    Assertions.assertFalse(repo.getEntitySet().isEmpty());

  }


}
