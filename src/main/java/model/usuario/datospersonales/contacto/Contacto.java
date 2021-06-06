package model.usuario.datospersonales.contacto;

/**
 * Datos de Contacto.
 * 
 * @author Tomás Sánchez
 * @since 06.06.2021
 * @version 1.0
 */
@FunctionalInterface
public interface Contacto {
  public void contactar(String mensaje);
}
