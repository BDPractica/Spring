package com.library.core.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "fiesta")
public class Fiesta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idFiestaa")
	private long id;

	private String ubicacion;

	@JsonFormat(pattern = "YYYY-MM-dd")
	private Date fecha;
	
	//para eliminar una fiesta es necesario quitar la anotacion cascadetipo all
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "persona_fiesta", 
	joinColumns = @JoinColumn(name = "fiesta_id", referencedColumnName = "idFiestaa"), 
	inverseJoinColumns = @JoinColumn(name = "persona_id", referencedColumnName = "idPersonaa"))
	private Set<Persona> lstPersona = new HashSet<>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Set<Persona> getLstPersona() {
		return lstPersona;
	}

	public void setLstPersona(Set<Persona> lstPersona) {
		this.lstPersona = lstPersona;
	}

}
