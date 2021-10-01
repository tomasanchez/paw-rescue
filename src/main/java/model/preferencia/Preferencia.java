package model.preferencia;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import db.PersistentEntity;
import model.publicacion.PublicacionDarEnAdopcion;

/**
 * Preferencias de Adopcion
 *
 * @version 1.0
 * @since Entrega III
 * @author Tomás Sánchez
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE )
public abstract class Preferencia extends PersistentEntity{
  
  public abstract boolean puedeRecomendarse(PublicacionDarEnAdopcion publicacion);
  
}
