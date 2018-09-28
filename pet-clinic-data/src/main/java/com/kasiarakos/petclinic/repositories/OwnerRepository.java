package com.kasiarakos.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import com.kasiarakos.petclinic.model.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
}
