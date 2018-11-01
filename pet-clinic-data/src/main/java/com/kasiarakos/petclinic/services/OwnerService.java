package com.kasiarakos.petclinic.services;

import java.util.Set;

import com.kasiarakos.petclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);

    Set<Owner>  findByLastNameLike(String lastName);
}
