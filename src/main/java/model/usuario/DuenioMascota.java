package model.usuario;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import model.mascota.Chapita;
import model.mascota.Mascota;
import model.publicacion.PublicacionDarEnAdopcion;
import model.usuario.datospersonales.DatosPersonales;

@Entity
public class DuenioMascota extends Usuario {

  @OneToMany
  @JoinColumn(name = "mascota_id")
  private List<Mascota> mascotas = new ArrayList<>();

  @ManyToOne
  @JoinColumn(name = "recomendacion_id")
  private PublicacionDarEnAdopcion recomendacion;

  public DuenioMascota() {
    super();
  }

  /**
   * Mascotas registadas.
   *
   * @param datosPersonales los datos relacionados al Nombre, DNI, etc.
   * @param usuario         el nombre de usuario
   * @param password        password válida.
   * @param mascotas        las mascotas registadas
   */
  public DuenioMascota(DatosPersonales datosPersonales, String usuario, String password, List<Mascota> mascotas) {
    super(usuario, password);
    this.mascotas = mascotas;
    this.datosPersonales = datosPersonales;
  }

  /**
   * Asocia una mascota a un usuario.
   * 
   * @param mascota la mascota a registrar
   */
  public void registrarMascota(Mascota mascota) {
    mascotas.add(mascota.setChapita(new Chapita(this)));
  }

  // public boolean esDuenio(int id) {return mascotas.stream().anyMatch(mascota ->
  // mascota.getChapita().getId() == id);}

  public List<Mascota> getMascotas() {
    return this.mascotas;
  }

  public Integer getCantidadMascotas() {
    return mascotas.size();
  }

  public DatosPersonales getDatosPeronales() {
    return this.datosPersonales;
  }

  public PublicacionDarEnAdopcion getRecomendacion() {
    return recomendacion;
  }

  public DuenioMascota recomendarAdopcion(PublicacionDarEnAdopcion recomendacion) {
    this.recomendacion = recomendacion;
    return this;
  }

}
