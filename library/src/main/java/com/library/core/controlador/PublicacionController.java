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

import com.library.core.entidades.Publicacion;
import com.library.core.excepciones.ResourceNotFoundException;
import com.library.core.repositorio.PublicacionRepositorio;

import jakarta.validation.Valid;

@RestController
public class PublicacionController {

	@Autowired
	private PublicacionRepositorio repositorio;
	
	@GetMapping("/publicaiones")
	public Page<Publicacion> ListarPublicaciones(Pageable pageable){
		return repositorio.findAll(pageable);
	}
	
	@PostMapping("/publicaciones")
	public Publicacion GuardarPublicacion(@Valid @RequestBody Publicacion publicacion) {
		return repositorio.save(publicacion);
	}
	
	@PutMapping("/publicaciones/{id}")
	public Publicacion ActualizarPublicaciones(@PathVariable long id, @Valid @RequestBody Publicacion publicacion) {
		return repositorio.findById(id).map(publi -> {
			publi.setDescripcion(publicacion.getDescripcion());
			publi.setTitulo(publicacion.getTitulo());
			return repositorio.save(publi);
		}).orElseThrow(() -> new ResourceNotFoundException("Publicacion con el ID: "+id+" no encontrado"));
	}
	
	@DeleteMapping("/publicaciones/{id}")
	public ResponseEntity<?> EliminarPublicaciones(@PathVariable long id) {
		return repositorio.findById(id).map(publi -> {
			repositorio.delete(publi);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("Publicacion con el ID: "+id+" no encontrado"));
	}
}
