package com.bahri.sfgpetclinic.services.springdatajpa;

import com.bahri.sfgpetclinic.model.Owner;
import com.bahri.sfgpetclinic.repositories.OwnerRepository;
import com.bahri.sfgpetclinic.repositories.PetRepository;
import com.bahri.sfgpetclinic.repositories.PetTypeRepository;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerSDJPAServiceTest {

    @Mock
    OwnerRepository ownerRepository;

    @Mock
    PetRepository petRepository;

    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    OwnerSDJPAService service;

    Owner returnedOwner;

    @BeforeEach
    void setUp() {
        returnedOwner = Owner.builder().id(1L).lastName("raed").build();
    }

    @Test
    void findAll() {
        Set<Owner> owners = new HashSet<>();
        owners.add(Owner.builder().id(1L).build());
        owners.add(Owner.builder().id(2L).build());

        when(ownerRepository.findAll()).thenReturn(owners);
        Set<Owner> owners1 = service.findAll();
        assertEquals(2,owners1.size());
    }

    @Test
    void findById() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnedOwner));
        Owner owner = service.findById(1L);
        assertNotNull(owner);
    }

    @Test
    void save() {
        Owner savedOwner = Owner.builder().id(1L).build();
        when(ownerRepository.save(any())).thenReturn(returnedOwner);
        Owner owner = service.save(savedOwner);
        verify(ownerRepository).save(any());
        assertEquals(owner.getId(),returnedOwner.getId());

    }

    @Test
    void delete() {
        service.delete(returnedOwner);
        verify(ownerRepository,times(1)).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(1L);
        assertEquals(service.findAll().size(),0);
    }

    @Test
    void findByLastName() {
        Owner owner1 = Owner.builder().id(1L).lastName("Raed").build();
        when(ownerRepository.findByLastName(any())).thenReturn(java.util.Optional.ofNullable(owner1));
        Owner owner = service.findByLastName("Raed");
        assert owner1 != null;
        assertEquals(owner.getLastName(),owner1.getLastName());
    }
}