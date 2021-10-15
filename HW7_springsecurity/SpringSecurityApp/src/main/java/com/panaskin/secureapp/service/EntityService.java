package com.panaskin.secureapp.service;

import java.util.List;
import java.util.Optional;

public interface EntityService<T> {

    List<T> receiveAll();
    Optional<T> receiveById(Long id);
    void add(T session);
}
