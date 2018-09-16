package com.kasiarakos.petclinic.services;

import com.kasiarakos.petclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);
}
