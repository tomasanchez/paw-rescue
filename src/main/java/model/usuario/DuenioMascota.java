package model.usuario;

import java.util.ArrayList;
import java.util.List;
import model.mascota.Chapita;
import model.mascota.Mascota;
import model.usuario.datospersonales.DatosPersonales;

public class DuenioMascota extends Usuario {

  List<Mascota> mascotas = new ArrayList<Mascota>();
  DatosPersonales datosPersonales;

  public DuenioMascota() {
    super();
  }

  /**
   * Mascotas registadas.
   *
   * @param datosPersonales los datos relacionados al Nombre, DNI, etc.
   * @param usuario el nombre de usuario
   * @param password password válida.
   * @param mascotas las mascotas registadas
   */
  public DuenioMascota(DatosPersonales datosPersonales, String usuario, String password,
      List<Mascota> mascotas) {
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

  public boolean esDuenio(int id) {
    return mascotas.stream().anyMatch(mascota -> mascota.getChapita().getId() == id);
  }

  public List<Mascota> getMascotas() {
    return this.mascotas;
  }

  public Integer getCantidadMascotas() {
    return mascotas.size();
  }

  public DatosPersonales getDatosPeronales() {
    return this.datosPersonales;
  }

}
