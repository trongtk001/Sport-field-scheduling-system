package com.example.sportfieldschedulingsystem.service;

import com.example.sportfieldschedulingsystem.dto.FieldDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface FieldService extends BaseService<FieldDTO>{

    int countTotalItem();

    int countTotalItemByType(String type);

    int countTotalItemContainNameOrAddress(String q);

    int countTotalItemContainNameOrAddressAndHaveType(String q, String type);

    List<FieldDTO> search(String q,Pageable pageable);

    List<FieldDTO> searchFilteredByType(String q, String type, Pageable pageable);

    List<FieldDTO> filterByType(String type, Pageable pageable);
}
