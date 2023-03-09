package com.library.core.entidades;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "biblioteca")
public class Biblioteca implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idBiblioteca")
	private long idBiblioteca;
	  
	//Esta anotacion es la libreria validation
	@NotNull
	@Column(name = "nombre")
	private String nombre;
	
	//Toda entidad que tenga mappedBy es porque no es la propietaria de la relacion
	@OneToMany(mappedBy = "idBiblioteca", cascade = CascadeType.ALL)
	private Set<Libro> libros = new HashSet<>();
	
	public Biblioteca() {
	}

	public long getIdBiblioteca() {
		return idBiblioteca;
	}

	public void setIdBiblioteca(long idBiblioteca) {
		this.idBiblioteca = idBiblioteca;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Libro> getLibros() {
		return libros;
	}

	//Cada ves que se esta estableciendo libros aqui se le indica con el for que la biblioteca que se va a establecer es la
	//actual el objeto actual en el que esta en el contexto 
	public void setLibros(Set<Libro> libros) {
		this.libros = libros;
		for(Libro libro : libros) {
			libro.setIdBiblioteca(this);
		}
	}
	
	
	
}
