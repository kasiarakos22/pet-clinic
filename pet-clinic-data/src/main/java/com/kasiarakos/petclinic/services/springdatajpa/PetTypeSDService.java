package com.kasiarakos.petclinic.services.springdatajpa;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.kasiarakos.petclinic.model.PetType;
import com.kasiarakos.petclinic.repositories.PetTypeRepository;
import com.kasiarakos.petclinic.services.PetTypeService;


@Service
@Profile("springdatajpa")
public class PetTypeSDService implements PetTypeService {

    private final PetTypeRepository petTypeRepository;

    public PetTypeSDService(PetTypeRepository petTypeRepository) {
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public Set<PetType> findAll() {
        return StreamSupport.stream(petTypeRepository.findAll().spliterator(), false).collect(Collectors.toSet());
    }

    @Override
    public PetType findById(Long id) {
        return petTypeRepository.findById(id).orElse(null);
    }

    @Override
    public PetType save(PetType pet) {
        return petTypeRepository.save(pet);
    }

    @Override
    public void delete(PetType pet) {
        petTypeRepository.delete(pet);
    }

    @Override
    public void deleteById(Long id) {
        petTypeRepository.deleteById(id);
    }
}
