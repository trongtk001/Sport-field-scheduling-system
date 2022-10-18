package com.example.sportfieldschedulingsystem.service.impl;

import com.example.sportfieldschedulingsystem.dto.FieldDTO;
import com.example.sportfieldschedulingsystem.entity.FieldEntity;
import com.example.sportfieldschedulingsystem.mapper.FieldMapper;
import com.example.sportfieldschedulingsystem.repository.FieldRepository;
import com.example.sportfieldschedulingsystem.service.FieldService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class FieldServiceImpl implements FieldService {

    private final FieldRepository fieldRepository;

    private final FieldMapper fieldMapper;

    public FieldServiceImpl(FieldRepository fieldRepository, FieldMapper fieldMapper) {
        this.fieldRepository = fieldRepository;
        this.fieldMapper = fieldMapper;
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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found this field");
        }
    }

    @Override
    public FieldDTO search(Long id) {
        FieldEntity fieldEntity =fieldRepository.findById(id).orElse(null);
        return fieldMapper.toDTO(fieldEntity);
    }

    @Override
    public Page<FieldDTO> search(String q,Pageable pageable) {
        Page<FieldEntity> fieldEntities = fieldRepository.findByFieldNameContainingOrAddressContaining(q, q, pageable);

        Page<FieldDTO> fieldDTOS = new PageImpl<>(fieldMapper.toDTOList(fieldEntities.getContent()), pageable, fieldEntities.getTotalElements());

        return fieldDTOS;
    }


    @Override
    public Page<FieldDTO> searchFilteredByType(String q, String type, Pageable pageable) {
        Page<FieldEntity> fieldEntities = fieldRepository.findByFieldNameContainingOrAddressContainingAndType(q, q, type, pageable);

        Page<FieldDTO> fieldDTOS = new PageImpl<>(fieldMapper.toDTOList(fieldEntities.getContent()), pageable, fieldEntities.getTotalElements());

        return fieldDTOS;
    }

    @Override
    public Page<FieldDTO> findAll(Pageable pageable) {
        Page<FieldEntity> fieldEntities = fieldRepository.findAll(pageable);

        Page<FieldDTO> fieldDTOS = new PageImpl<>(fieldMapper.toDTOList(fieldEntities.getContent()), pageable, fieldEntities.getTotalElements());

        return fieldDTOS;
    }

    @Override
    public Page<FieldDTO> findAllByStatus(boolean status, Pageable pageable) {
        Page<FieldEntity> fieldEntities = fieldRepository.findAllByStatus(status, pageable);

        Page<FieldDTO> fieldDTOS = new PageImpl<>(fieldMapper.toDTOList(fieldEntities.getContent()), pageable, fieldEntities.getTotalElements());

        return fieldDTOS;
    }

    @Override
    public Page<FieldDTO> filterByType(String type, Pageable pageable) {
        Page<FieldEntity> fieldEntities = fieldRepository.findByType(type, pageable);

        Page<FieldDTO> fieldDTOS = new PageImpl<>(fieldMapper.toDTOList(fieldEntities.getContent()), pageable, fieldEntities.getTotalElements());

        return fieldDTOS;
    }

    @Override
    public int countTotalItem() {
        return (int) fieldRepository.count();
    }

}
