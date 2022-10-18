package com.example.sportfieldschedulingsystem.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BaseService<T> {

    T save(T dto);

    T delete(long id);

    T search(Long id);

    Page<T> findAll(Pageable pageable);
}
