package model.mascota;

import model.usuario.DuenioMascota;

public class Chapita {
  DuenioMascota owner;
  int id;

  public Chapita(DuenioMascota owner) {
    this.owner = owner;
  }

  public DuenioMascota getDuenio() {
    return owner;
  }
  
  public int getId() {
    return this.id;
  }
}
