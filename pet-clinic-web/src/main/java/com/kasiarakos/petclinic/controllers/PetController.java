package com.kasiarakos.petclinic.controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kasiarakos.petclinic.model.Owner;
import com.kasiarakos.petclinic.model.Pet;
import com.kasiarakos.petclinic.model.PetType;
import com.kasiarakos.petclinic.services.OwnerService;
import com.kasiarakos.petclinic.services.PetService;
import com.kasiarakos.petclinic.services.PetTypeService;

@Controller
@RequestMapping(value = "/owners/{ownerId}/pets")
public class PetController {

    private final PetService petService;
    private final PetTypeService petTypeService;
    private final OwnerService ownerService;

    public PetController(PetService petService, PetTypeService petTypeService, OwnerService ownerService) {
        this.petService = petService;
        this.petTypeService = petTypeService;
        this.ownerService = ownerService;
    }

    @ModelAttribute("types")
    public Collection<PetType> populatePetTypes() {
        return petTypeService.findAll();
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable("ownerId") Long ownerId) {
        return ownerService.findById(ownerId);
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/new")
    public String initCreate(Owner owner, Model model){
        Pet pet = Pet.builder().build();
        owner.getPets().add(pet);
        model.addAttribute(pet);
        return "pets/createOrUpdatePetForm";
    }

    public String processCreate(Owner owner, @Valid Pet pet, BindingResult result, ModelMap model){

        if (StringUtils.hasLength(pet.getName()) && pet.isNew() && owner.getPet(pet.getName(), true) != null){
            result.rejectValue("name", "duplicate", "already exists");
        }
        owner.getPets().add(pet);
        if (result.hasErrors()) {
            model.put("pet", pet);
            return "pets/createOrUpdatePetForm";
        }

        petService.save(pet);
        return "redirect:/owners/" + owner.getId();
    }
}
