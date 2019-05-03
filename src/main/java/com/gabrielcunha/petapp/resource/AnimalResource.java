package com.gabrielcunha.petapp.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielcunha.petapp.event.RecursoCriadoEvent;
import com.gabrielcunha.petapp.model.Animal;
import com.gabrielcunha.petapp.service.AnimalService;

@RestController
@RequestMapping("/animais")
public class AnimalResource {
	
	@Autowired
	private AnimalService animalService;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public List<Animal> listar() {
		return this.animalService.listar();
	}

	@GetMapping("/{id}")
	public Animal buscarPorId(@PathVariable Long id) throws Exception {
		Animal animal = this.animalService.buscarPor(id);		
	
		return animal;
	}
	
	
	@PostMapping
	public ResponseEntity<Animal> criar(@RequestBody @Valid Animal animal, HttpServletResponse response) {
		Animal animalSalvo = this.animalService.criar(animal);
		
		this.publisher.publishEvent(new RecursoCriadoEvent(this, response, animalSalvo.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	
	
}
