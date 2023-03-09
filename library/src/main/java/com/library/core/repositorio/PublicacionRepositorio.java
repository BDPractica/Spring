package com.library.core.repositorio;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.core.entidades.Publicacion;

@Repository("com.library.core.repositorio.PublicacionRepositorio")
public interface PublicacionRepositorio extends JpaRepository<Publicacion, Serializable> {

}
