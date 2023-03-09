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
import com.library.core.repositorio.FiestaRepositorio;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/fiesta")
public class FiestaController {

	@Autowired
	@Qualifier("com.library.core.repositorio.FiestaRepositorio")
	private FiestaRepositorio fiestaRepositorio;
	
	@GetMapping
	public ResponseEntity<Collection<Fiesta>> ListarFiesta() {
		return new ResponseEntity<>(fiestaRepositorio.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Fiesta> ListarFiestaId(
			@PathVariable long id
			){
		Fiesta fiesta = fiestaRepositorio.findById(id).orElseThrow();
		if(fiesta != null) {
			return new ResponseEntity<>(fiestaRepositorio.findById(id).orElseThrow(), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> GuardarPersona(@Valid @RequestBody Fiesta fiesta){
		return new ResponseEntity<>(fiestaRepositorio.save(fiesta), HttpStatus.CREATED);
	} 
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> EliminarFiesta(@PathVariable long id){
		fiestaRepositorio.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
