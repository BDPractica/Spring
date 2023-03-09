package com.library.core.entidades;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "persona")
public class Persona implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idPersonaa")
	private long id;
	
	@NotNull
	@Column(name = "nombre", length = 20)
	private String nombre;
	
	@NotNull
	@Column(name = "edad")
	private int edad;

	@OneToMany(mappedBy = "idPersona", cascade = CascadeType.ALL)
	private Set<Habilidad> lstHablidad = new HashSet<>();
	
	//para eliminar una fiesta es necesario quitar la anotacion cascadetipo all
	@ManyToMany(cascade = CascadeType.ALL)
	//Evita problemas de recursividad, para que no nos retorne una lista de fiesta infinitas, tambien para
	//que se omita este campo a la hora de serializar, si se va a persistir, pero no ha serializar
	@JsonBackReference
	@JoinTable(name = "persona_fiesta",
	joinColumns = @JoinColumn(name="persona_id", referencedColumnName = "idPersonaa"),
	inverseJoinColumns = @JoinColumn(name="fiesta_id", referencedColumnName  ="idFiestaa"))
	private Set<Fiesta> lstFiesta = new HashSet<>();
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public Set<Habilidad> getLstHablidad() {
		return lstHablidad;
	}

	public void setLstHablidad(Set<Habilidad> lstHablidad) {
		this.lstHablidad = lstHablidad;
	}

	public Set<Fiesta> getLstFiesta() {
		return lstFiesta;
	}

	public void setLstFiesta(Set<Fiesta> lstFiesta) {
		this.lstFiesta = lstFiesta;
	}
}
