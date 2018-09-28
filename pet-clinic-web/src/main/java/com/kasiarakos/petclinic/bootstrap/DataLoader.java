package com.kasiarakos.petclinic.bootstrap;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.kasiarakos.petclinic.model.Owner;
import com.kasiarakos.petclinic.model.Pet;
import com.kasiarakos.petclinic.model.PetType;
import com.kasiarakos.petclinic.model.Specialty;
import com.kasiarakos.petclinic.model.Vet;
import com.kasiarakos.petclinic.services.OwnerService;
import com.kasiarakos.petclinic.services.PetTypeService;
import com.kasiarakos.petclinic.services.VetServicce;
import com.kasiarakos.petclinic.services.map.SpecialtyServiceMap;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetServicce vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyServiceMap specialtyServiceMap;

    public DataLoader(OwnerService ownerService, VetServicce vetService, PetTypeService petTypeService, SpecialtyServiceMap specialtiesServiceMap) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyServiceMap = specialtiesServiceMap;
    }

    @Override
    public void run(String... args) {
        int count = petTypeService.findAll().size();
        if(count == 0) {
            loadData();
        }
    }

    private void loadData() {
        PetType dog =new PetType();
        dog.setName("Dog");
        dog = petTypeService.save(dog);
        PetType cat =new PetType();
        cat.setName("Cat");
        cat = petTypeService.save(cat);
        Owner kasiarakos = new Owner();
        kasiarakos.setFirstName("Dimitris");
        kasiarakos.setLastName("kasiaras");
        kasiarakos.setAddress("Penrose street 46");
        kasiarakos.setCity("Athens");
        kasiarakos.setTelephone("1234567890");
        Owner sofia = new Owner();
        sofia.setLastName("karka");
        sofia.setFirstName("Sofia");
        kasiarakos.setAddress("Penrose street 46");
        kasiarakos.setCity("Athens");
        kasiarakos.setTelephone("1234567890");
        ownerService.save(kasiarakos);
        ownerService.save(sofia);
        Pet kasiarakosPet = new Pet();
        kasiarakosPet.setPetType(dog);
        kasiarakosPet.setBirthDate(LocalDate.of(2018,9,22));
        kasiarakosPet.setName("jack");
        kasiarakosPet.setOwner(kasiarakos);
        kasiarakos.getPets().add(kasiarakosPet);
        Pet sofiaPet = new Pet();
        sofiaPet.setPetType(cat);
        sofiaPet.setBirthDate(LocalDate.of(2018,9,22));
        sofiaPet.setName("fluffy");
        sofiaPet.setOwner(sofia);
        kasiarakos.getPets().add(sofiaPet);
        Specialty radiology = new Specialty();
        radiology.setDescription("Radiology description");
        radiology = specialtyServiceMap.save(radiology);
        Specialty surgery = new Specialty();
        surgery.setDescription("Surgery description");
        surgery = specialtyServiceMap.save(surgery);
        Specialty dentirstry = new Specialty();
        dentirstry.setDescription("Dentistry description");
        dentirstry = specialtyServiceMap.save(dentirstry);
        Vet nefeli = new Vet();
        nefeli.setLastName("newcomer");
        nefeli.setFirstName("nefeli");
        nefeli.getSpecialties().add(radiology);
        Vet vet = new Vet();
        vet.setLastName("kostas");
        vet.setFirstName("papadopoulos");
        vet.getSpecialties().add(surgery);
        vet.getSpecialties().add(dentirstry);
        vetService.save(nefeli);
        vetService.save(vet);
    }
}
