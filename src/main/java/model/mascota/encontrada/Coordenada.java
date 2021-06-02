package model.mascota.encontrada;

public class Coordenada {

  String coordenadaX;
  String coordenadaY;

  public Coordenada(String coordenadaX, String coordenadaY) {
    this.coordenadaX = coordenadaX;
    this.coordenadaY = coordenadaY;
  }

  public String getCoordenadaX() {
    return this.coordenadaX;
  }

  public String getCoordenadaY() {
    return this.coordenadaY;
  }
}
