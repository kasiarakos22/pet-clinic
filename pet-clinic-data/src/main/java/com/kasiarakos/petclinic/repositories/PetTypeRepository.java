package com.kasiarakos.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import com.kasiarakos.petclinic.model.PetType;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
