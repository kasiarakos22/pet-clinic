package com.kasiarakos.petclinic.controllers;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.kasiarakos.petclinic.model.Vet;
import com.kasiarakos.petclinic.services.VetService;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

@ExtendWith(MockitoExtension.class)
class VetControllerTest {

    @Mock
    private VetService vetService;

    @InjectMocks
    private VetController vetController;

    Set<Vet> vets = new HashSet<>();
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        vets.add(Vet.builder().id(1L).build());
        vets.add(Vet.builder().id(2L).build());
        mockMvc = MockMvcBuilders.standaloneSetup(vetController).build();
    }

    @Test
    void listVets() throws Exception {
        Mockito.when(vetService.findAll()).thenReturn(vets);

        mockMvc.perform(MockMvcRequestBuilders.get("/vets/index"))
               .andExpect(MockMvcResultMatchers.view().name("vets/index"))
               .andExpect(MockMvcResultMatchers.model().attribute("vets", hasSize(2)));
    }
}
