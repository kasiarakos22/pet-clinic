package com.kasiarakos.petclinic.bootstrap;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.kasiarakos.petclinic.model.Owner;
import com.kasiarakos.petclinic.model.Pet;
import com.kasiarakos.petclinic.model.PetType;
import com.kasiarakos.petclinic.model.Specialty;
import com.kasiarakos.petclinic.model.Vet;
import com.kasiarakos.petclinic.model.Visit;
import com.kasiarakos.petclinic.services.OwnerService;
import com.kasiarakos.petclinic.services.PetTypeService;
import com.kasiarakos.petclinic.services.SpecialtyService;
import com.kasiarakos.petclinic.services.VetServicce;
import com.kasiarakos.petclinic.services.VisitService;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetServicce vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;
    private final VisitService visitService;

    public DataLoader(
        OwnerService ownerService, VetServicce vetService, PetTypeService petTypeService, SpecialtyService specialtyService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) {
        int count = petTypeService.findAll().size();
        if (count == 0) {
            loadData();
        }
    }

    private void loadData() {
        /**Initializing pet Types*/
        PetType dog = new PetType();
        dog.setName("Dog");
        dog = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        cat = petTypeService.save(cat);

        /**Initializing Owners*/
        Owner kasiarakos = new Owner();
        kasiarakos.setFirstName("Dimitris");
        kasiarakos.setLastName("kasiaras");
        kasiarakos.setAddress("Penrose street 46");
        kasiarakos.setCity("Athens");
        kasiarakos.setTelephone("1234567890");

        Owner sofia = new Owner();
        sofia.setLastName("karka");
        sofia.setFirstName("Sofia");
        sofia.setAddress("Kokkinaki 35");
        sofia.setCity("Athens");
        sofia.setTelephone("1234567890");

        /**Initializing pets*/
        Pet kasiarakosPet = new Pet();
        kasiarakosPet.setPetType(dog);
        kasiarakosPet.setBirthDate(LocalDate.of(2018, 9, 22));
        kasiarakosPet.setName("jack");
        kasiarakosPet.setOwner(kasiarakos);

        Pet sofiasPet = new Pet();
        sofiasPet.setPetType(cat);
        sofiasPet.setBirthDate(LocalDate.of(2018, 9, 22));
        sofiasPet.setName("fluffy");
        sofiasPet.setOwner(sofia);

        kasiarakos.getPets().add(kasiarakosPet);
        sofia.getPets().add(sofiasPet);
        ownerService.save(kasiarakos);
        ownerService.save(sofia);

        /**Initializing Specialties*/
        Specialty radiology = new Specialty();
        radiology.setDescription("Radiology description");
        radiology = specialtyService.save(radiology);

        Specialty surgery = new Specialty();
        surgery.setDescription("Surgery description");
        surgery = specialtyService.save(surgery);

        Specialty dentistry = new Specialty();
        dentistry.setDescription("Dentistry description");
        dentistry = specialtyService.save(dentistry);

        /**Initializing Vets*/
        Vet nefeli = new Vet();
        nefeli.setLastName("newcomer");
        nefeli.setFirstName("nefeli");
        nefeli.getSpecialties().add(radiology);

        Vet vet = new Vet();
        vet.setLastName("kostas");
        vet.setFirstName("papadopoulos");

        vet.getSpecialties().add(surgery);
        vet.getSpecialties().add(dentistry);
        vetService.save(nefeli);
        vetService.save(vet);

        /**Initializing Visit*/
        Visit catVisit = new Visit();
        catVisit.setPet(sofiasPet);
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("fluffy kitty");
        visitService.save(catVisit);
    }
}
