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
  
  public double distancia (){
    int x =  Integer.parseInt(coordenadaX);
    int y =  Integer.parseInt(coordenadaY);
    return Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
  }
}
