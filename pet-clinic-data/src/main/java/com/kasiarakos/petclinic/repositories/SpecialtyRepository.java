package com.kasiarakos.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import com.kasiarakos.petclinic.model.Specialty;

public interface SpecialtyRepository extends CrudRepository<Specialty, Long> {
}
