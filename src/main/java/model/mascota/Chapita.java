package model.mascota;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import db.PersistentEntity;
import model.usuario.Usuario;

@Entity
@Table(name = "Chapitas")
public class Chapita extends PersistentEntity {

  @ManyToOne
  Usuario owner;

  public Chapita() {}

  public Chapita(Usuario usuario) {
    this.owner = usuario;
  }

  public Usuario getDuenio() {
    return owner;
  }
}
