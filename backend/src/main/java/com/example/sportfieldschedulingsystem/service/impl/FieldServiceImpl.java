package com.example.sportfieldschedulingsystem.service.impl;

import com.example.sportfieldschedulingsystem.dto.FieldDTO;
import com.example.sportfieldschedulingsystem.entity.FieldEntity;
import com.example.sportfieldschedulingsystem.mapper.FieldMapper;
import com.example.sportfieldschedulingsystem.repository.FieldRepository;
import com.example.sportfieldschedulingsystem.service.FieldService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FieldServiceImpl implements FieldService {

    private final FieldRepository fieldRepository;

    private final FieldMapper fieldMapper;

    public FieldServiceImpl(FieldRepository fieldRepository, FieldMapper fieldMapper) {
        this.fieldRepository = fieldRepository;
        this.fieldMapper = fieldMapper;
    }

    @Override
    public FieldDTO createNew(FieldDTO dto) {
        return null;
    }

    @Override
    public FieldDTO save(FieldDTO dto) {
        FieldEntity entity;
        if (dto.getId() != null) {
            FieldEntity oldEntity = fieldRepository.findById(dto.getId()).orElse(null);
            entity = fieldMapper.toEntity(dto, oldEntity);
        } else {
            entity = fieldMapper.toEntity(dto);
        }
        entity = fieldRepository.save(entity);
        return fieldMapper.toDTO(entity);
    }

    @Override
    public FieldDTO delete(long id) {
        FieldEntity entity = fieldRepository.findById(id).orElse(null);
        if (entity != null) {
            fieldRepository.delete(entity);
            return fieldMapper.toDTO(entity);
        } else {
            return null;
        }
    }

    @Override
    public List<FieldDTO> findAll(Pageable pageable) {
        List<FieldDTO> dtoList = new ArrayList<>();
        List<FieldEntity> entityList = fieldRepository.findAll(pageable).getContent();
        entityList.forEach(entity -> {
            dtoList.add(fieldMapper.toDTO(entity));
        });
        return dtoList;
    }

    @Override
    public int countTotalItem() {
        return (int) fieldRepository.count();
    }

    @Override
    public int countTotalItemContainNameOrAddress(String q) {
        return (int) fieldRepository.countByFieldNameContainingOrAddressContaining(q, q);
    }

    @Override
    public int countTotalItemContainNameOrAddressAndHaveType(String q, String type) {
        return (int) fieldRepository.countByFieldNameContainingOrAddressContainingAndType(q, q, type);
    }

    @Override
    public int countTotalItemByType(String type) {
        return (int) fieldRepository.countByType(type);
    }

    @Override
    public List<FieldDTO> search(String q,Pageable pageable) {
        List<FieldEntity> entityList = fieldRepository.findByFieldNameContainingOrAddressContaining(q, q,pageable);

        List<FieldDTO> dtoList = fieldMapper.toDTOList(entityList);

        return dtoList;
    }

    @Override
    public List<FieldDTO> searchFilteredByType(String q, String type, Pageable pageable) {
        List<FieldEntity> entityList = fieldRepository.findByFieldNameContainingOrAddressContainingAndType(q, q, type, pageable);

        List<FieldDTO> dtoList = fieldMapper.toDTOList(entityList);

        return dtoList;
    }

    @Override
    public List<FieldDTO> filterByType(String type, Pageable pageable) {
        List<FieldEntity> entityList = fieldRepository.findByType(type, pageable);

        List<FieldDTO> dtoList = fieldMapper.toDTOList(entityList);

        return dtoList;
    }
}
