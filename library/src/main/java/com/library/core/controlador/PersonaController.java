package com.library.core.controlador;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.core.entidades.Fiesta;
import com.library.core.entidades.Persona;
import com.library.core.repositorio.PersonaRepositorio;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/persona")
public class PersonaController {

	@Autowired
	@Qualifier("com.library.core.repositorio.PersonaRepositorio")
	private PersonaRepositorio personaRepositorio;
	
	@GetMapping
	public ResponseEntity<Collection<Persona>> ListarPersona() {
		return new ResponseEntity<>(personaRepositorio.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Persona> ListarPersonaId(
			@PathVariable long id
			){
		Persona persona = personaRepositorio.findById(id).orElseThrow();
		if(persona != null) {
			return new ResponseEntity<>(personaRepositorio.findById(id).orElseThrow(), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/{id}/fiestas")
	public ResponseEntity<Collection<Fiesta>> ListarFiestaPersona(
				@PathVariable long id){
		Persona persona = personaRepositorio.findById(id).orElseThrow();
		if(persona != null) {
			return new ResponseEntity<>(persona.getLstFiesta(), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> GuardarPersona(@Valid @RequestBody Persona persona){
		return new ResponseEntity<>(personaRepositorio.save(persona), HttpStatus.CREATED);
	} 
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> EliminarPersona(@PathVariable long id){
		personaRepositorio.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
