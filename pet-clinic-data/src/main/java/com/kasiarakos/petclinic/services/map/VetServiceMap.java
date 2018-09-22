package com.kasiarakos.petclinic.services.map;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.kasiarakos.petclinic.model.Vet;
import com.kasiarakos.petclinic.services.SpecialtyService;
import com.kasiarakos.petclinic.services.VetServicce;

@Service
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetServicce {

    private final SpecialtyService specialtyService;

    public VetServiceMap(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }

    @Override
    public Vet save(Vet object) {
        if(object.getSpecialties().size() > 0){
            object.getSpecialties().forEach(specialty -> {
                if(specialty.getId() == null){
                    specialtyService.save(specialty);
                }
            });
        }
        return super.save(object);
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }
}
