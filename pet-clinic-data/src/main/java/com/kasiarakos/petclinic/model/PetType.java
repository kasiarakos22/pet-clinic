package com.kasiarakos.petclinic.model;

import javax.persistence.Entity;

import lombok.Builder;

@Entity
public class PetType extends BaseEntity{

    private String name;

    @Builder
    public PetType(Long id, String name) {
        super(id);
        this.name = name;
    }

    public PetType(){
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
