package com.kasiarakos.petclinic.services.springdatajpa;

import com.kasiarakos.petclinic.model.Owner;
import com.kasiarakos.petclinic.repositories.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerSDServiceTest {

    private static final String LAST_NAME = "kasiarakos";
    private static final Long USER_ID = 1L;

    @Mock
    private OwnerRepository ownerRepository;
    @InjectMocks
    private OwnerSDService ownerSDService;

    private Owner returnOwner;

    @BeforeEach
    void setUp() {
        returnOwner = new Owner();
        returnOwner.setId(USER_ID);
        returnOwner.setLastName(LAST_NAME);
    }

    @Test
    void findByLastName() {
        when(ownerRepository.findByLastName(eq(LAST_NAME))).thenReturn(returnOwner);

        Owner resultOwner = ownerSDService.findByLastName(LAST_NAME);
        verify(ownerRepository, times(1)).findByLastName(eq(LAST_NAME));

        assertEquals(returnOwner, resultOwner);
    }

    @Test
    void findAll() {
        Owner returnOwner2 = new Owner();
        returnOwner2.setId(2L);

        Set<Owner> expectedSet = new HashSet<>();
        expectedSet.add(returnOwner);
        expectedSet.add(returnOwner2);

        when(ownerRepository.findAll()).thenReturn(expectedSet);

        Set<Owner> resultOwnerSet = ownerSDService.findAll();

        assertEquals(expectedSet.size(), resultOwnerSet.size());
        assertTrue(resultOwnerSet.contains(returnOwner));
        assertTrue(resultOwnerSet.contains(returnOwner2));
    }

    @Test
    void findById() {
        when(ownerRepository.findById(eq(USER_ID))).thenReturn(Optional.of(returnOwner));

        Owner resultOwner = ownerSDService.findById(USER_ID);

        assertEquals(returnOwner, resultOwner);
    }

    @Test
    void findByIdNotExisted() {
        when(ownerRepository.findById(eq(USER_ID))).thenReturn(Optional.empty());

        Owner resultOwner = ownerSDService.findById(USER_ID);

        assertNull(resultOwner);
    }

    @Test
    void save() {

        when(ownerRepository.save(returnOwner)).thenReturn(returnOwner);

        Owner resultOwner = ownerSDService.save(returnOwner);

        assertEquals(resultOwner, resultOwner);
    }

    @Test
    void delete() {
        ownerSDService.delete(returnOwner);
        verify(ownerRepository, times(1)).delete(eq(returnOwner));
    }

    @Test
    void deleteById() {
        ownerSDService.deleteById(USER_ID);
        verify(ownerRepository, times(1)).deleteById(eq(USER_ID));
    }
}