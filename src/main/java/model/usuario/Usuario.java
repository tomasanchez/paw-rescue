package model.usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import db.PersistentEntity;
import model.usuario.datospersonales.DatosPersonales;
import model.usuario.datospersonales.contacto.DatosContacto;
import services.usuario.contacto.notificaciones.NotificadorAPI;

/**
 * Usuario común.
 * 
 * @author Kenti
 * @version 2.0
 */
@MappedSuperclass
public abstract class Usuario extends PersistentEntity {

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

  /**
   * Medios de comunicación preferidos por el usuario.
   * 
   * @since 3.0
   */
  @Transient
  private List<NotificadorAPI> notificadorAPIs = new ArrayList<>();

  public Usuario() {
  }

  /**
   * Instancia un nuevo usuario.
   *
   * @param usuario  nombre de usuario.
   * @param password una password válida.
   */
  public Usuario(String usuario, String password) {
    this.usuario = Objects.requireNonNull(usuario);
    this.password = Objects.requireNonNull(password);
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

}
