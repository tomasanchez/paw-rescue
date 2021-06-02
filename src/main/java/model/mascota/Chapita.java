package model.mascota;

import model.usuario.DuenioMascota;

public class Chapita {
  DuenioMascota owner;

  public Chapita(DuenioMascota owner) {
    this.owner = owner;
  }

  public DuenioMascota getDuenio() {
    return owner;
  }
}
