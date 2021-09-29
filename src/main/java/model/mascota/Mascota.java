package model.mascota;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import db.PersistentEntity;
import model.mascota.caracteristica.Caracteristica;
import model.mascota.encontrada.MascotaEncontrada;

@Entity
public class Mascota extends PersistentEntity{

  private String nombre;
  private String apodo;
  private int edad;
  
  @Enumerated(EnumType.STRING)
  private Sexo sexo;
  private String descripcionFisica;
  @OneToMany
  @JoinColumn(name = "mascota_id")
  private List<String> fotos;
  
  @Enumerated(EnumType.STRING)
  private TipoMascota tipoMascota;
  @Transient
  private List<Caracteristica> caracteristicas;
  @OneToOne
  private Chapita chapita;

  /**
   * Instancia una mascota.
   * 
   * @param nombre el nombre de la mascota.
   * @param apodo un apodo.
   * @param tipoMascota el tipo de animal.
   * @param edad su edad en años.
   * @param sexo el sexo del animal.
   * @param descripcionFisica una descripción física.
   * @param fotos Lista de URLs de fotos.
   */
  
  public Mascota(String nombre, String apodo, TipoMascota tipoMascota, int edad, Sexo sexo,
      String descripcionFisica, List<String> fotos, Chapita chapita) {
    this.nombre = nombre;
    this.apodo = apodo;
    this.tipoMascota = tipoMascota;
    this.edad = edad;
    this.sexo = sexo;
    this.descripcionFisica = descripcionFisica;
    this.fotos = fotos;
    this.caracteristicas = new ArrayList<>();
    this.chapita = chapita;
  }

  public Mascota() {
    this.caracteristicas = new ArrayList<Caracteristica>();
    this.fotos = new ArrayList<String>();
  }

  public String getNombre() {
    return this.nombre;
  }

  public String getApodo() {
    return this.apodo;
  }

  public String getDescripcionFisica() {
    return this.descripcionFisica;
  }

  public TipoMascota getTipoMascota() {
    return this.tipoMascota;
  }

  public Sexo getSexo() {
    return this.sexo;
  }

  public int getEdad() {
    return this.edad;
  }

  public List<String> getFotos() {
    return this.fotos;
  }

  public List<Caracteristica> getCaracteristicas() {
    return this.caracteristicas;
  }

  public Mascota setChapita(Chapita chapita) {
    this.chapita = chapita;
    return this;
  }

  public Chapita getChapita() {
    return this.chapita;
  }

  public Mascota addCaracteristica(Caracteristica caracteristica) {
    this.caracteristicas.add(caracteristica);
    return this;
  }

  /**
   * Busca si posee el tipo de caracteristica.
   * 
   * @param caracteristica la caracteristica
   * @return si posee o no la caracteristica.
   */
  public Boolean poseeCaracteristica(Caracteristica caracteristica) {
    return caracteristicas.stream().anyMatch(c -> c.equals(caracteristica));
  }

  public boolean esLaMismaMascota(MascotaEncontrada mascotaEncontrada) {
    return getChapita().equals(mascotaEncontrada.getChapita());
  }

  public Mascota setTipo(TipoMascota tipo) {
    this.tipoMascota = tipo;
    return this;
  }
}
