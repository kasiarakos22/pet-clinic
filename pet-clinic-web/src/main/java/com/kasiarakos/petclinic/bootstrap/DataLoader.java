package com.kasiarakos.petclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.kasiarakos.petclinic.model.Owner;
import com.kasiarakos.petclinic.model.PetType;
import com.kasiarakos.petclinic.model.Vet;
import com.kasiarakos.petclinic.services.OwnerService;
import com.kasiarakos.petclinic.services.PetTypeService;
import com.kasiarakos.petclinic.services.VetServicce;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetServicce vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetServicce vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) {
        PetType dog =new PetType();
        dog.setName("Dog");
        dog = petTypeService.save(dog);

        PetType cat =new PetType();
        cat.setName("Cat");
        cat = petTypeService.save(cat);

        Owner kasiarakos = new Owner();
        kasiarakos.setFirstName("Dimitris");
        kasiarakos.setLastName("kasiaras");

        Owner sofia = new Owner();
        sofia.setLastName("karka");
        sofia.setFirstName("Sofia");

        ownerService.save(kasiarakos);
        ownerService.save(sofia);

        Vet nefeli = new Vet();
        nefeli.setLastName("newcomer");
        nefeli.setFirstName("nefeli");

        Vet vet = new Vet();
        vet.setLastName("kostas");
        vet.setFirstName("papadopoulos");

        vetService.save(nefeli);
        vetService.save(vet);
    }
}
