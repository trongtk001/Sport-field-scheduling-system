package com.example.sportfieldschedulingsystem.service;

import com.example.sportfieldschedulingsystem.dto.FieldDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



public interface FieldService extends BaseService<FieldDTO>{

    int countTotalItem();

    Page<FieldDTO> search(String q,Pageable pageable);

    Page<FieldDTO> searchFilteredByType(String q, String type, Pageable pageable);

    Page<FieldDTO> findAllByStatus(boolean status, Pageable pageable);

    Page<FieldDTO> filterByType(String type, Pageable pageable);
}
