package com.kasiarakos.petclinic.services.map;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.kasiarakos.petclinic.model.PetType;
import com.kasiarakos.petclinic.services.PetTypeService;

@Service
public class PetTypeServiceMap extends AbstractMapService<PetType, Long> implements PetTypeService {

    @Override
    public Set<PetType> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public PetType save(PetType object) {
        return super.save(object);
    }

    @Override
    public void delete(PetType object) {
         super.delete(object);
    }

    @Override
    public PetType findById(Long id) {
        return super.findById(id);
    }
}