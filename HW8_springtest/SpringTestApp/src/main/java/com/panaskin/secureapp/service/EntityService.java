package com.panaskin.secureapp.service;

import java.util.List;
import java.util.Optional;

public interface EntityService<T> {

    List<T> findAll();
    T findById(Long id);
    T save(T entity);
}
