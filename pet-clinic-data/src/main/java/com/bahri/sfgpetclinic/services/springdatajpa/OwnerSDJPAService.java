package com.bahri.sfgpetclinic.services.springdatajpa;

import com.bahri.sfgpetclinic.model.Owner;
import com.bahri.sfgpetclinic.repositories.OwnerRepository;
import com.bahri.sfgpetclinic.repositories.PetRepository;
import com.bahri.sfgpetclinic.services.OwnerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class OwnerSDJPAService implements OwnerService {

    private final OwnerRepository ownerRepository;
    public OwnerSDJPAService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public Set<Owner> findAll() {

        Set<Owner> owners= new HashSet<>();
        ownerRepository.findAll().iterator().forEachRemaining(owners::add);
        return owners;
    }

    @Override
    public Owner findById(Long aLong) {
        Optional<Owner> owner = ownerRepository.findById(aLong);
        return owner.orElse(null);
    }

    @Override
    public Owner save(Owner object) {
        return ownerRepository.save(object);
    }

    @Override
    public void delete(Owner object) {
        ownerRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
      ownerRepository.deleteById(aLong);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return ownerRepository.findByLastName(lastName).orElse(null);
    }
}
