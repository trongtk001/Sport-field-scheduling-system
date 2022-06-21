package com.example.sportfieldschedulingsystem.mapper;

import com.example.sportfieldschedulingsystem.dto.FieldDTO;
import com.example.sportfieldschedulingsystem.entity.FieldEntity;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FieldMapper {

    private final ModelMapper modelMapper;

    public FieldMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public FieldEntity toEntity(FieldDTO dto) {
        FieldEntity entity = modelMapper.map(dto, FieldEntity.class);
        return entity;
    }

    public FieldEntity toEntity(FieldDTO dto, FieldEntity entity) {
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(dto, entity);
        return entity;
    }

    public FieldDTO toDTO(FieldEntity entity) {
        FieldDTO dto = modelMapper.map(entity, FieldDTO.class);
        return dto;
    }

    public List<FieldEntity> toEntityList(List<FieldDTO> fieldDTOS) {
        List<FieldEntity> fieldEntities = new ArrayList<>();

        fieldDTOS.forEach((fieldDTO -> {
            fieldEntities.add(modelMapper.map(fieldDTO, FieldEntity.class));
        }));

        return fieldEntities;
    }

    public List<FieldDTO> toDTOList(List<FieldEntity> fieldEntities) {
        List<FieldDTO> fieldDTOS = new ArrayList<>();

        fieldEntities.forEach(entity -> {
            fieldDTOS.add(modelMapper.map(entity, FieldDTO.class));
        });

        return fieldDTOS;
    }

}
