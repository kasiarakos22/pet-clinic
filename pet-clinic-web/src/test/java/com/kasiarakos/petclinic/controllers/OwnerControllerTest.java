package com.kasiarakos.petclinic.controllers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.hamcrest.core.IsInstanceOf;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.kasiarakos.petclinic.model.Owner;
import com.kasiarakos.petclinic.services.OwnerService;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    private static final Long USER_ID = 1L;

    @Mock
    OwnerService ownerService;

    @InjectMocks
    OwnerController ownerController;

    private Set<Owner> owners = new HashSet<>();
    MockMvc mockMvc;

    @BeforeEach
    private void setUp(){
        owners.add(Owner.builder().id(1L).build());
        owners.add(Owner.builder().id(2L).build());
        mockMvc = MockMvcBuilders
                    .standaloneSetup(ownerController)
                    .build();
    }


    @Test
    void processFindOwnersReturnOne() throws Exception {
        when(ownerService.findByLastNameLike(any(String.class))).thenReturn(Stream.of(Owner.builder().id(1L).build()).collect(Collectors.toSet()));

        mockMvc.perform(get("/owners"))
               .andExpect(status().is3xxRedirection())
               .andExpect(view().name("redirect:/owners/1"))
               .andExpect(model().attribute("owner", is(IsInstanceOf.instanceOf(Owner.class))));
    }


    @Test
    void showOwner() throws Exception {
        when(ownerService.findById(eq(USER_ID))).thenReturn(Owner.builder().id(USER_ID).build());

        mockMvc.perform(get("/owners/"+USER_ID))
               .andExpect(status().isOk())
               .andExpect(view().name("owners/ownerDetails"))
               .andExpect(model().attribute("owner", hasProperty("id", is(USER_ID))));
    }

    @Test
    void findOwnersNoResults() throws Exception {

        mockMvc.perform(get("/owners/find"))
               .andExpect(status().isOk())
               .andExpect(view().name("owners/findOwners"))
               .andExpect(model().attributeExists("owner"));
    }

    @Test
    void processFindFormReturnMany() throws Exception {
        when(ownerService.findByLastNameLike(anyString()))
            .thenReturn(new HashSet<>(Arrays.asList(Owner.builder().id(1l).build(),
                Owner.builder().id(2l).build())));

        mockMvc.perform(get("/owners"))
               .andExpect(status().isOk())
               .andExpect(view().name("owners/ownersList"))
               .andExpect(model().attribute("selections", hasSize(2)));
    }


}
