package com.kasiarakos.petclinic.services.springdatajpa;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.kasiarakos.petclinic.model.Visit;
import com.kasiarakos.petclinic.repositories.VisitRepository;
import com.kasiarakos.petclinic.services.VisitService;

@Service
@Profile("springdatajpa")
public class VisitSDService implements VisitService {

    private final VisitRepository visitRepository;

    public VisitSDService(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    @Override
    public Set<Visit> findAll() {
        return StreamSupport.stream(visitRepository.findAll().spliterator(), false).collect(Collectors.toSet());
    }

    @Override
    public Visit findById(Long id) {
        return visitRepository.findById(id).orElse(null);
    }

    @Override
    public Visit save(Visit visit) {
        return visitRepository.save(visit);
    }

    @Override
    public void delete(Visit visit) {
        visitRepository.delete(visit);
    }

    @Override
    public void deleteById(Long id) {
        visitRepository.deleteById(id);
    }
}
