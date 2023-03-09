package com.library.core.entidades;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

//La clase que sera la propietaria de la relaciion va a ser libro 
@Entity
@Table(name = "libro")
public class Libro implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idLibro")
	private long idLibro;

	// Esta anotacion es la libreria validation
	@NotNull
	@Column(name = "nombre")
	private String nombre;

	// Esta anotacion es la libreria validation
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	//Con esta anotacion se evita el error que genera trabajar con LAZY el error es LAzyinitialationException
	//tambien para que la sesion de apiREST ignore el error a serializar en una cadena JSON 
	@JsonProperty(access = Access.WRITE_ONLY)
	//El name recibe el nombre del campo que tiene la llave foraniea en la BDD, joinColumn indica cual es la entidad
	//dueña 
	@JoinColumn(name = "idBiblioteca")
	private Biblioteca idBiblioteca;

	public Libro() {
	}

	public long getIdLibro() {
		return idLibro;
	}

	public void setIdLibro(long idLibro) {
		this.idLibro = idLibro;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Biblioteca getIdBiblioteca() {
		return idBiblioteca;
	}

	public void setIdBiblioteca(Biblioteca idBiblioteca) {
		this.idBiblioteca = idBiblioteca;
	}
}
