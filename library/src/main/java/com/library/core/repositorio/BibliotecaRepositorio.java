package com.library.core.repositorio;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.core.entidades.Biblioteca;

@Repository("com.library.core.repositorio.BibliotecaRepositorio")
public interface BibliotecaRepositorio extends JpaRepository<Biblioteca, Serializable> {

}
