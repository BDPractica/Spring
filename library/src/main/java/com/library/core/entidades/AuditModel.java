package com.library.core.entidades;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
//Permite entidades para mantener las propiedades basicas, no se asocia con ninguna tabla ni con ninguna BDD, con esta anotacion se indica
//que esta no sera una clase entidad. esto crea una super clase para que se pueda implementar en este casa en las tablas de publicacion y  
//comentario ecisten dos campos en comun que es FECHA_ACTUALIZACION Y FECHA_CREACION, entonces como se tienen en ambas las mismas se crea 
//una superclase con esos campos en comun de las tablas 
@MappedSuperclass
//Con esta anotacion se le indica a JPa por medio de AuditingEntityListener.class que es una oyente de entidad JPa que actualiza
//la informacion 
@EntityListeners(AuditingEntityListener.class)
//con esta anotacion sirve para que esto no se serialize para que lo omita y solo le permite los get para el momento que se quiera obtener 
@JsonIgnoreProperties(value = { "fechaCreacion", "fechaActualizacion" }, allowGetters = true)
public abstract class AuditModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_creacion", nullable = false, updatable = false)
	@CreatedDate
	private Date fechaCreacion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_actualizacion", nullable = false)
	@LastModifiedDate
	private Date fechaActualizacion;

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

}
