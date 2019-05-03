package com.gabrielcunha.petapp.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabrielcunha.petapp.model.Animal;
import com.gabrielcunha.petapp.repository.AnimalRepository;

@Service
public class AnimalService {

	@Autowired
	private AnimalRepository animalRepository;
	

	public List<Animal> listar() {
		return animalRepository.findAll();
	}
	
	
	public Animal buscarPor(Long id) throws Exception {
		Animal animal;
		
		try {
			
			Optional<Animal> animalOptional =  animalRepository.findById(id);
			
			animal = animalOptional.isPresent() ? animalOptional.get() : null; 
			
		} catch (Exception e) {
			
			throw new Exception(e.getMessage());
		}
		
		return animal;
	}


	public Animal criar(@Valid Animal animal) {
		return animalRepository.save(animal);
	}


	
	
}
