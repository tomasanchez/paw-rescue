package Rest.Response;

import org.codehaus.jackson.annotate.JsonProperty;


public class Ubicacion {
  String direccion;
  @JsonProperty("lat")
  Double coordenadaX;
  @JsonProperty("long")
  Double coordenadaY;


  public Ubicacion() {
  }

  public void setCoordenadaX(Double coordenadaX) {
    this.coordenadaX = coordenadaX;
  }

  public void setCoordenadaY(Double coordenadaY) {
    this.coordenadaY = coordenadaY;
  }

  public String getDireccion() {
    return direccion;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }


  public Double getCoordenadaX() {
    return this.coordenadaX;
  }

  public Double getCoordenadaY() {
    return this.coordenadaY;
  }

  public void setLat(Double coordenadaX) {
    this.coordenadaX = coordenadaX;
  }

  public void setLong(Double coordenadaY) {
    this.coordenadaY = coordenadaY;
  }

  public Double getLat() {
    return this.coordenadaX;
  }

  public Double getLong() {
    return this.coordenadaY;
  }
}
