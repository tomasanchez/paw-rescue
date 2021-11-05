package db;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.MappedSuperclass;
import javax.persistence.NoResultException;

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
    return ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
        .getActualTypeArguments()[0]).getSimpleName();
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

  /**
   * Realiza una consulta para obtener una entidad en particular.
   * 
   * @param id el identificador de la entidad
   * @return la entidad o NULL
   */
  @SuppressWarnings("unchecked")
  public T getEntity(long id) {

    try {
      return (T) entityManager().createQuery("FROM " + getTableName() + " T WHERE T.id LIKE :id")
          .setParameter("id", id).getSingleResult();
    } catch (NoResultException exception) {
      return null;
    }
  }

  /**
   * Actualiza una entidad del Set de datos.
   * 
   * @param entity la entidad a actualizarse
   * @return la entidad actualizada
   */
  public T updateEntity(T entity) {
    return entityManager().merge(entity);
  }

  public T updateEntity(Long id) {
    return updateEntity(getEntity(id));
  }

  /**
   * Elimina una entidad de la base de datos.
   * 
   * @param entity la entidad a eliminarse
   */
  public void deleteEntity(T entity) {
    entityManager().remove(entity);
  }

  /**
   * Elimina una entidad de la base de datos.
   * 
   * @param entity la entidad a eliminarse
   */
  public void deleteEntity(Long id) {
    deleteEntity(getEntity(id));
  }
}
