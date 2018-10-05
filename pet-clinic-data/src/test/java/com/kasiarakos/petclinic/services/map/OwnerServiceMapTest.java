package com.kasiarakos.petclinic.services.map;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.kasiarakos.petclinic.model.Owner;
import com.kasiarakos.petclinic.services.PetService;
import com.kasiarakos.petclinic.services.PetTypeService;

import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceMapTest {

    private static final Long OWNER_ID = 1L;
    private static final String OWNER_LAST_NAME = "kasiarakos";
    private OwnerServiceMap ownerServiceMap;

    @Mock
    private PetTypeService petTypeService;
    @Mock
    private PetService petService;


    @BeforeEach
    void setUp() {

        MockitoAnnotations.initMocks(this);
        ownerServiceMap = new OwnerServiceMap(petTypeService, petService);
        Owner owner = new Owner();
        owner.setLastName(OWNER_LAST_NAME);
        ownerServiceMap.save(owner);
    }

    @Test
    void findAll() {
        Owner expectedOwner = new Owner();
        expectedOwner.setId(OWNER_ID);
        expectedOwner.setLastName(OWNER_LAST_NAME);
        Set<Owner> resultOwners = ownerServiceMap.findAll();
        assertEquals(1,resultOwners.size());
        assertTrue(resultOwners.contains(expectedOwner));
    }

    @Test
    void deleteById() {
        ownerServiceMap.deleteById(OWNER_ID);
        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void delete() {
        Owner ownerToDelete = new Owner();
        ownerToDelete.setId(OWNER_ID);
        ownerToDelete.setLastName(OWNER_LAST_NAME);
        ownerServiceMap.delete(ownerToDelete);
        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void saveWithoutId() {
        Owner savedOwner = ownerServiceMap.save(new Owner());
        assertNotNull(savedOwner);
        assertEquals((Long)2L, savedOwner.getId());
    }

    @Test
    void saveWithId() {
        Owner expectedOwner = new Owner();
        expectedOwner.setId(2L);
        Owner savedOwner = ownerServiceMap.save(expectedOwner);
        assertEquals(expectedOwner,savedOwner);
    }

    @Test
    void findById() {
        Owner expectedOwner = new Owner();
        expectedOwner.setId(OWNER_ID);
        expectedOwner.setLastName(OWNER_LAST_NAME);
        Owner resultOwner = ownerServiceMap.findById(OWNER_ID);
        assertEquals(expectedOwner,resultOwner);
    }

    @Test
    void findByLastName() {
        Owner expectedOwner = new Owner();
        expectedOwner.setId(OWNER_ID);
        expectedOwner.setLastName(OWNER_LAST_NAME);

        Owner resultOwner = ownerServiceMap.findByLastName(OWNER_LAST_NAME);
        assertEquals(expectedOwner, resultOwner);
    }
}
