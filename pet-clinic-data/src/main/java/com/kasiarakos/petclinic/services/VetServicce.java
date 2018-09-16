package com.kasiarakos.petclinic.services;

import java.util.Set;

import com.kasiarakos.petclinic.model.Vet;

public interface VetServicce {

    Vet findById(Long id);

    Vet save(Vet vet);

    Set<Vet> findAll();
}
