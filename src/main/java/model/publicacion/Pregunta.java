package model.publicacion;

public class Pregunta {
  boolean estaContestada = false;
  
  void contestar(){
    this.estaContestada = true;
  }
  
  public boolean getEstaContestada(){
    return this.estaContestada;
  }
}
