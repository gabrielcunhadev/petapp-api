package com.gabrielcunha.petapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabrielcunha.petapp.model.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long>{

//	@Query("select a from Animal a where a.id = :id")
//	Animal consultar(@Param("id") Long id);
	
}
