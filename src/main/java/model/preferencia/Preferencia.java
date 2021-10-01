package model.preferencia;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import db.PersistentEntity;

/**
 * Preferencias de Adopcion
 *
 * @version 1.0
 * @since Entrega III
 * @author Tomás Sánchez
 */
@Entity
@DiscriminatorColumn(name= "tipo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE )
public abstract class Preferencia extends PersistentEntity{
  
}
