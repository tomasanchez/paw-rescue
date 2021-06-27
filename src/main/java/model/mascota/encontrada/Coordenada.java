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
  
  public Double distancia (Coordenada otraCoordenada){
    Integer earthRadius = 6371;

    Double deltaLatitude = Math.toRadians(Double.parseDouble(otraCoordenada.getCoordenadaX()) - Double.parseDouble(this.getCoordenadaX()));

    Double deltaLongitude = Math.toRadians(Double.parseDouble(otraCoordenada.getCoordenadaY()) - Double.parseDouble(this.getCoordenadaY()));

    Double a = Math.sin(deltaLatitude / 2) * Math.sin(deltaLatitude / 2) + Math.cos(Math.toRadians(Double.parseDouble(this.getCoordenadaY()))) *
      Math.cos(Math.toRadians(Double.parseDouble(otraCoordenada.getCoordenadaX()))) * Math.sin(deltaLongitude / 2) * Math.sin(deltaLongitude / 2);

    Double distanceAngular = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

    return earthRadius * distanceAngular;
  }
  
}
