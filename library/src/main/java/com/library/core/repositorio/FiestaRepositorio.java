package com.library.core.repositorio;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.library.core.entidades.Fiesta;

@Repository("com.library.core.repositorio.FiestaRepositorio")
public interface FiestaRepositorio extends CrudRepository<Fiesta, Serializable> {
	Collection<Fiesta> findAll();
}
