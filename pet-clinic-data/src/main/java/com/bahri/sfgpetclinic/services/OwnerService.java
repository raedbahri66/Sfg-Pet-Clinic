package com.bahri.sfgpetclinic.services;

import com.bahri.sfgpetclinic.model.Owner;

import java.util.List;

public interface OwnerService extends CrudService<Owner, Long>{

    Owner findByLastName(String lastName);
    List<Owner> findAllByLastNameLike(String lastName);

}
