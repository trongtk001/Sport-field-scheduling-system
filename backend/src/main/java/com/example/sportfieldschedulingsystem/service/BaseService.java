package com.example.sportfieldschedulingsystem.service;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BaseService<T> {
    T createNew(T dto);

    T save(T dto);

    T delete(long id);

    List<T> findAll(Pageable pageable);
}
