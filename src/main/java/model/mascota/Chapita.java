package model.mascota;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import db.PersistentEntity;
import model.usuario.DuenioMascota;

@Entity
@Table(name = "Chapitas")
public class Chapita extends PersistentEntity {
  @ManyToOne
  DuenioMascota owner;
  /* int id; */

  public Chapita() {
  }

  public Chapita(DuenioMascota owner) {
    this.owner = owner;
  }

  public DuenioMascota getDuenio() {
    return owner;
  }

  /*
   * public int getId() { return this.id; }
   */
}
