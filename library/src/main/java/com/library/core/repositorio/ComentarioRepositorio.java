package com.library.core.repositorio;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.core.entidades.Comentario;

@Repository("com.library.core.repositorio.ComentarioRepositorio")
public interface ComentarioRepositorio extends JpaRepository<Comentario, Serializable> {

	Page<Comentario> findByPublicacion(long id, Pageable pageable);

	Optional<Comentario> findByIdAndPublicacionId(long comentarioID, long publicacionId);
}
