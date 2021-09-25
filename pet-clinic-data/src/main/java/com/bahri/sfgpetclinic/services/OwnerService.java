package com.bahri.sfgpetclinic.services;

import com.bahri.sfgpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long>{

    Owner findByLastName(String lastName);

}
