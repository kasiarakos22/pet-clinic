package com.kasiarakos.petclinic.services.springdatajpa;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.kasiarakos.petclinic.model.Specialty;
import com.kasiarakos.petclinic.repositories.SpecialtyRepository;
import com.kasiarakos.petclinic.services.SpecialtyService;

@Service
@Profile("springdatajpa")
public class SpecialtySDService implements SpecialtyService {

    private final SpecialtyRepository specialtyRepository;

    public SpecialtySDService(SpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }

    @Override
    public Set<Specialty> findAll() {
        return StreamSupport.stream(specialtyRepository.findAll().spliterator(),false).collect(Collectors.toSet());
    }

    @Override
    public Specialty findById(Long id) {
        return specialtyRepository.findById(id).orElse(null);
    }

    @Override
    public Specialty save(Specialty specialty) {
        return specialtyRepository.save(specialty);
    }

    @Override
    public void delete(Specialty specialty) {
        specialtyRepository.delete(specialty);
    }

    @Override
    public void deleteById(Long id) {
        specialtyRepository.deleteById(id);
    }
}
