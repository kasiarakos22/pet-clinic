package com.kasiarakos.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import com.kasiarakos.petclinic.model.Vet;

public interface VetRepository extends CrudRepository<Vet, Long> {
}
