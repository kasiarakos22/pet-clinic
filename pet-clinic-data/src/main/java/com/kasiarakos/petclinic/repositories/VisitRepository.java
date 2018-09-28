package com.kasiarakos.petclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import com.kasiarakos.petclinic.model.Visit;

public interface VisitRepository extends CrudRepository<Visit, Long> {
}
