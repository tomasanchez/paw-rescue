package db;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.MappedSuperclass;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

@MappedSuperclass
public abstract class PersistentEntitySet<T> implements WithGlobalEntityManager {

  /**
   * Obtiene el nombre de la tabla de la cual crear queries.
   * 
   * @return el nombre de la tabla
   */
  @SuppressWarnings("unchecked")
  protected String getTableName() {
    return ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0])
        .getSimpleName();
  }

  /**
   * Realiza una lectura de todas las entidades.
   * 
   * @return el set de entidades
   */
  @SuppressWarnings("unchecked")
  public List<T> getEntitySet() {
    return entityManager().createQuery("from " + getTableName()).getResultList();
  }

  /**
   * Persiste una entidad.
   * 
   * @param entity la entidad a persistir
   * @return la entidad persistida
   */
  public T createEntity(T entity) {
    entityManager().persist(entity);
    return entity;
  }
}
