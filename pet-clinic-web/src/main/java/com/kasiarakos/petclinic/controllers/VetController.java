package com.kasiarakos.petclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kasiarakos.petclinic.services.VetServicce;

@Controller
public class VetController {

    private final VetServicce vetServicce;

    public VetController(VetServicce vetServicce) {
        this.vetServicce = vetServicce;
    }

    @RequestMapping({"/vets", "/vets.html", "vets/index", "vets/index.html"})
    public String listVets(Model model){

        model.addAttribute("vets", vetServicce.findAll());
        return "vets/index";
    }
}
