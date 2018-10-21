package com.kasiarakos.petclinic.services.springdatajpa;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.kasiarakos.petclinic.model.Vet;
import com.kasiarakos.petclinic.repositories.VetRepository;
import com.kasiarakos.petclinic.services.VetService;

@Service
@Profile("springdatajpa")
public class VetSDService implements VetService {

    private final VetRepository vetRepository;

    public VetSDService(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    @Override
    public Set<Vet> findAll() {
        return StreamSupport.stream(vetRepository.findAll().spliterator(), false).collect(Collectors.toSet());
    }

    @Override
    public Vet findById(Long id) {
        return vetRepository.findById(id).orElse(null);
    }

    @Override
    public Vet save(Vet vet) {
        return vetRepository.save(vet);
    }

    @Override
    public void delete(Vet vet) {
        vetRepository.delete(vet);
    }

    @Override
    public void deleteById(Long id) {
        vetRepository.deleteById(id);
    }
}
