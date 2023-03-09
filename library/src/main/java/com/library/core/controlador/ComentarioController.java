package com.library.core.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.library.core.entidades.Comentario;
import com.library.core.excepciones.ResourceNotFoundException;
import com.library.core.repositorio.ComentarioRepositorio;
import com.library.core.repositorio.PublicacionRepositorio;

import jakarta.validation.Valid;

@RestController	
public class ComentarioController {

	@Autowired
	private ComentarioRepositorio comentarioRepositorio;
	
	@Autowired
	private PublicacionRepositorio publicacionRepositorio;
	
	@GetMapping("/publicaciones/{idPublicacion}/comentarios")
	public Page<Comentario> ListarComentarioPorPublicacion(@PathVariable(value = "idPublicacion") long id, Pageable pageable){
		return comentarioRepositorio.findByPublicacion(id, pageable);
	}
	
	@PostMapping("/publicaciones/{idPublicacion}/comentarios")
	public Comentario GuardarComentario(@PathVariable(value = "idPublicacion") long id, @Valid @RequestBody Comentario comentario) {
		return publicacionRepositorio.findById(id).map( publi ->{
			comentario.setPublicacion(publi);
			return comentarioRepositorio.save(comentario);
		}).orElseThrow(() -> new ResourceNotFoundException("Publicacion con el ID: "+id+" no encontrado"));
	}
	
	@PutMapping("/publicaciones/{publicacionId}/comentarios/{comentarioId}")
	public Comentario actualizarComentario(@PathVariable(value = "publicacionId") Long publicacionId,@PathVariable(value = "comentarioId") Long comentarioId,@Valid @RequestBody Comentario comentarioRequest) {
		if(!publicacionRepositorio.existsById(publicacionId)) {
			throw new ResourceNotFoundException("Publicacion con el ID : " + publicacionId + " no encontrada");
		}
		
		return comentarioRepositorio.findById(comentarioId).map(comentario -> {
			comentario.setTexto(comentarioRequest.getTexto());
			return comentarioRepositorio.save(comentario);
		}).orElseThrow(() -> new ResourceNotFoundException("Comentario con el ID : " + comentarioId + " no encontrado"));
	}
	
	@DeleteMapping("/publicaciones/{publicacionId}/comentarios/{comentarioId}")
	public ResponseEntity<?> eliminarComentario(@PathVariable(value = "publicacionId") Long publicacionId,@PathVariable(value = "comentarioId") Long comentarioId){
		return comentarioRepositorio.findByIdAndPublicacionId(comentarioId, publicacionId).map(comentario -> {
			comentarioRepositorio.delete(comentario);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("Comentario con el ID : " + comentarioId + " no encontrado y publicacion con el ID : " + publicacionId + " no encontrada"));
	}
}
