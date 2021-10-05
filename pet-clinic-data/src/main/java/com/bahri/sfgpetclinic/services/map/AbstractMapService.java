package com.bahri.sfgpetclinic.services.map;

import com.bahri.sfgpetclinic.model.BaseEntity;

import java.util.*;

public abstract class AbstractMapService<T extends BaseEntity,ID extends Long> {

    protected Map<Long, T> map = new HashMap<>();

    T findById( ID id){
        return map.get(id);
    }
    Set<T> findAll(){
        return new HashSet<>(map.values());
    }
    T save(T object){
        if (object != null) {
            if (object.getId() !=null) {
                object.setId(getNextId());
            } else {
                throw new RuntimeException("Object cannont be null");
            }
            map.put(object.getId(),object);
        }
        return object;
    }
    void delete(T object){
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }
    void deleteById(ID id){
        map.remove(id);
    }
    private Long getNextId() {
        Long nextId = null;
        try {
            nextId = Collections.max(map.keySet()) + 1;
        } catch (NoSuchElementException ex) {
            nextId = 1L;
        }
        return nextId;
    }
}
