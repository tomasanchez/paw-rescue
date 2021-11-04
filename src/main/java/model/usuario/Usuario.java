package model.usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import db.PersistentEntity;
import model.mascota.Chapita;
import model.mascota.Mascota;
import model.publicacion.Asociacion;
import model.publicacion.PublicacionDarEnAdopcion;
import model.usuario.datospersonales.DatosPersonales;
import model.usuario.datospersonales.contacto.DatosContacto;
import services.usuario.contacto.ServicioNotificacion;
import services.usuario.contacto.notificaciones.NotificadorAPI;

/**
 * Usuario común.
 * 
 * @author Kenti
 * @version 2.0
 */
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario", "mail"})})
public class Usuario extends PersistentEntity {

  /**
   * Usuario de logging.
   * 
   * @since 1.0
   */
  String usuario;

  /**
   * Password del usuario.
   * 
   * @since 1.0
   */
  String password;

  /**
   * Los datos relacionados al nombre, apellido, documento, contacto.
   * 
   * @since 2.0
   */
  @Embedded
  DatosPersonales datosPersonales;

  @Enumerated(EnumType.ORDINAL)
  Privilegio privilegio;

  /**
   * Medios de comunicación preferidos por el usuario.
   * 
   * @since 3.0
   */
  @Transient
  private List<NotificadorAPI> notificadorAPIs = new ArrayList<>();

  @ManyToOne
  @JoinColumn(name = "asociacion_id")
  Asociacion asociacion;


  @OneToMany
  @JoinColumn(name = "mascota_id")
  private List<Mascota> mascotas = new ArrayList<>();

  // @ManyToOne
  // @JoinColumn(name = "recomendacion_id")
  @Transient
  private PublicacionDarEnAdopcion recomendacion;


  public Usuario() {}

  /**
   * Instancia un nuevo usuario.
   *
   * @param usuario nombre de usuario.
   * @param password una password válida.
   */
  public Usuario(String usuario, String password) {
    this(usuario, password, null, new ArrayList<>());
  }

  /**
   * Mascotas registadas.
   *
   * @param datosPersonales los datos relacionados al Nombre, DNI, etc.
   * @param usuario el nombre de usuario
   * @param password password válida.
   * @param mascotas las mascotas registadas
   */
  public Usuario(String usuario, String password, DatosPersonales datosPersonales,
      List<Mascota> mascotas) {
    this.usuario = Objects.requireNonNull(usuario);
    this.password = Objects.requireNonNull(password);
    this.mascotas = mascotas;
    this.datosPersonales = datosPersonales;
  }

  public Asociacion getAsociacion() {
    return asociacion;
  }

  public void setAsociacion(Asociacion asociacion) {
    this.asociacion = asociacion;
  }

  public String getUsuario() {
    return this.usuario;
  }

  public String getPassword() {
    return this.password;
  }

  public void notificar(String msg) {
    for (NotificadorAPI api : notificadorAPIs) {
      api.notificar(datosPersonales.getDatosContacto(), msg);
    }
  }

  public void contactar(DatosContacto contacto2, String msg) {
    for (NotificadorAPI api : notificadorAPIs) {
      api.contactar(datosPersonales.getDatosContacto(), contacto2, msg);
    }
  }

  public void agregarNotificador(NotificadorAPI notificador) {
    notificadorAPIs.add(notificador);
  }

  public void eliminarNotificador(NotificadorAPI notificador) {
    notificadorAPIs.remove(notificador);
  }

  public Usuario setUsuario(String usuario) {
    this.usuario = usuario;
    return this;
  }

  public Usuario setPassword(String password) {
    this.password = password;
    return this;
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

  public Usuario recomendarAdopcion(PublicacionDarEnAdopcion recomendacion) {
    this.recomendacion = recomendacion;
    ServicioNotificacion.getInstance().notificarPosibleAdopcion(this, recomendacion);
    return this;
  }

  public Usuario setPrivilege(Privilegio privilegios) {
    this.privilegio = privilegios;
    return this;
  }

  public Privilegio getPrivileges() {
    return this.privilegio;
  }
}
