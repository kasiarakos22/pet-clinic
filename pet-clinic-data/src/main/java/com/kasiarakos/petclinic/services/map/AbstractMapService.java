package com.kasiarakos.petclinic.services.map;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.kasiarakos.petclinic.model.BaseEntity;

public class AbstractMapService<T extends BaseEntity, ID extends Number> {

    protected Map<Long , T> map = new HashMap<>();

    Set<T> findAll(){
        return new HashSet<>(map.values());
    }

    T findById(ID id){
        return map.get(id);
    }

    T save(T object){
        if(object != null ){
            if(object.getId() == null){
                object.setId(getNextId());
            }
        }else {
            throw new RuntimeException("Object cannot be null");
        }
        map.put(object.getId(), object);
        return object;
    }

    void deleteById(ID id){
        map.remove(id);
    }

    void delete(T object){
        map.entrySet().removeIf(e  -> e.getValue().equals(object));
    }

    private Long getNextId(){
        Long nextId = null;
        try {
            nextId = Collections.max(map.keySet()) + 1;
        }catch (Exception e){
            nextId = 1L;
        }
        return nextId;
    }
}
