package com.library.core.controlador;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.core.entidades.Biblioteca;
import com.library.core.entidades.Libro;
import com.library.core.repositorio.BibliotecaRepositorio;
import com.library.core.repositorio.LibroRepositorio;

import jakarta.validation.Valid;

//Cuando se usa la anotacion restcontroller es para indicar que esto es una APIREST
//y que se trabaja con formato de tipo JSON cuando se transmite y se recibe data
@RestController
@RequestMapping("/api/libro")
public class LibroControlador {

	@Autowired
	@Qualifier("com.library.core.repositorio.BibliotecaRepositorio")
	private BibliotecaRepositorio bibliotecaRepositorio;

	@Autowired
	@Qualifier("com.library.core.repositorio.LibroRepositorio")
	private LibroRepositorio libroRepositorio;

	// peticion para enviar datos, para que el cliente pueda enviar datos se utiliza
	// post y para que se envie en tipo JSON
	// se utiliza la anotacion RequestBody, tambien se le indica que se van utilizar
	// validaciones, ya que se en las entidades
	// se usa la anotacion NotNull
	@PostMapping
	public ResponseEntity<Libro> guardarBiblioteca(@Valid @RequestBody Libro libro) {
		Optional<Biblioteca> bibliotecaOptional = bibliotecaRepositorio
				.findById(libro.getIdBiblioteca().getIdBiblioteca());
		// Basicamente aqui dice que si es diferente a que si encontro algo
		if (!bibliotecaOptional.isPresent()) {
			System.out.println("no existe");
			return ResponseEntity.unprocessableEntity().build();
		}
		libro.setIdBiblioteca(bibliotecaOptional.get());
		Libro libroGuardado = libroRepositorio.save(libro);
		
		System.out.println("Existe");
		/*
		 * Aqui dos formas de retornar con responseEntity que esto srive para enviar una
		 * respuesta mas detallada del comportamiento del metodo NOTA: la variable URI
		 * ubicacion hace uso de ServletUriComponentsBuilder ya que esta trabaja de la
		 * mano con variables tipo URI y tener en cuenta que el nombre Servlet es porque
		 * nuestro controlador es nuestro servlet
		 */
//		URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//				.buildAndExpand(bibliotecaGuardada.getIdBiblioteca()).toUri();
//		return ResponseEntity.created(ubicacion).body(bibliotecaGuardada);
		
		return new ResponseEntity<Libro>(libroGuardado, HttpStatus.CREATED);

	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<Libro> EditarLibro(@PathVariable long id,
			@Valid @RequestBody Libro libro) {
		// Optional puede retornal dos opciones true o false
		Optional<Biblioteca> bibliotecaOptional = bibliotecaRepositorio.findById(libro.getIdBiblioteca().getIdBiblioteca());

		// Basicamente aqui dice que si es diferente a que si encontro algo
		if (!bibliotecaOptional.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}

		Optional<Libro> libroOptional = libroRepositorio.findById(id);
		if (!libroOptional.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}
		libro.setIdBiblioteca(bibliotecaOptional.get());
		libro.setIdLibro(libroOptional.get().getIdLibro());
		libroRepositorio.save(libro);

		// Aqui se dice que no se se esta enviando ningun contenido
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Libro> EliminarLibro(@PathVariable long id) {
		// Optional puede retornal dos opciones true o false
		Optional<Libro> optional = libroRepositorio.findById(id);

		// Basicamente aqui dice que si es diferente a que si encontro algo
		if (!optional.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}

		libroRepositorio.delete(optional.get());
		// Aqui se dice que no se se esta enviando ningun contenido
		return ResponseEntity.noContent().build();
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Libro> ObtenerLibroId(@PathVariable long id) {
		// Optional puede retornal dos opciones true o false
		Optional<Libro> optional = libroRepositorio.findById(id);

		// Basicamente aqui dice que si es diferente a que si encontro algo
		if (!optional.isPresent()) {
			return ResponseEntity.unprocessableEntity().build();
		}

		// Aqui se dice que todo se ejecuto bien y envia la informacion por JSON
		return ResponseEntity.ok(optional.get());
	}

	@GetMapping
	public ResponseEntity<Page<Libro>> ListarLibro(Pageable pageable) {
		return ResponseEntity.ok(libroRepositorio.findAll(pageable));
	}

}
