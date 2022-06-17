package com.afridevteam.gestionstock.service;

import java.util.List;

public interface EntityService<T> {

    T save(T dto);

    T findById(Long id);

    List<T> findAll();

    void delete(Long id);
    
}
