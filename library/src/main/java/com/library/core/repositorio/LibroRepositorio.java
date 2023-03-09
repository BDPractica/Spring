package com.library.core.repositorio;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.core.entidades.Libro;
@Repository("com.library.core.repositorio.LibroRepositorio")
public interface LibroRepositorio extends JpaRepository<Libro, Serializable> {

}
