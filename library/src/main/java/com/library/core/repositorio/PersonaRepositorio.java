package com.library.core.repositorio;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.library.core.entidades.Persona;

@Repository("com.library.core.repositorio.PersonaRepositorio")
public interface PersonaRepositorio extends CrudRepository<Persona, Serializable> {
	Collection<Persona> findAll();
}
