package com.kasiarakos.petclinic.controllers;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kasiarakos.petclinic.model.Owner;
import com.kasiarakos.petclinic.services.OwnerService;

@RequestMapping("/owners")
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllAllowedFields(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }

    @RequestMapping({"/find"})
    public String findOwners(Model model){
        Owner owner = new Owner();
        model.addAttribute("owner", owner);
        return "owners/findOwners";
    }

    @RequestMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable Long ownerId){
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject(ownerService.findById(ownerId));
        return mav;
    }

    @GetMapping
    public String processingForm(Owner owner,  BindingResult result, Model model){

        if(owner.getLastName() == null){
            owner.setLastName("");
        }
        Set<Owner> owners =  ownerService.findByLastNameLike("%"+owner.getLastName()+"%");
        if(owners.isEmpty()){
            result.rejectValue("lastName", "notFound", "not found");
            return "owners/findOwners";
        }
        else if(owners.size()== 1){
            Owner resultOwner = owners.iterator().next();
            return "redirect:/owners/"+resultOwner.getId();
        } else {
            model.addAttribute("selections", owners);
            return "owners/ownersList";
        }

    }

    @GetMapping(value = "/new")
    public String initCreate(Model model){
        model.addAttribute(Owner.builder().build());
        return "owners/createOrUpdateOwnerForm";
    }

    @PostMapping(value = "new")
    public String processCreate(@Valid Owner owner, BindingResult result){

        if(result.hasErrors()){
            return "owners/createOrUpdateOwnerForm";
        }

        Owner savedOwner = ownerService.save(owner);
        return "redirect:/owners/"+savedOwner.getId();
    }

    @GetMapping(value = "/{ownerId}/edit")
    public String initUpdate(@PathVariable Long ownerId, Model model){
        Owner owner = ownerService.findById(ownerId);
        model.addAttribute(owner);
        return "owners/createOrUpdateOwnerForm";
    }

    @PostMapping(value = "/{ownerId}/edit")
    public String processUpdate(@Valid Owner owner, BindingResult result, @PathVariable Long ownerId){
        if(result.hasErrors()){
            return "owners/createOrUpdateOwnerForm";
        }

        owner.setId(ownerId);
        Owner savedOwner = ownerService.save(owner);
        return "redirect:/owners/"+savedOwner.getId();
    }

}
