package com.kasiarakos.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import com.kasiarakos.petclinic.model.Pet;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
