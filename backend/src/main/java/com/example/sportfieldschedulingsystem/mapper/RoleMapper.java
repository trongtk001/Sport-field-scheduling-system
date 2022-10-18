package com.example.sportfieldschedulingsystem.mapper;

import com.example.sportfieldschedulingsystem.dto.RoleDTO;
import com.example.sportfieldschedulingsystem.entity.RoleEntity;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoleMapper {

    private final ModelMapper modelMapper;

    public RoleMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public RoleEntity toEntity(RoleDTO dto) {
        RoleEntity entity = modelMapper.map(dto, RoleEntity.class);
        return entity;
    }

    public RoleEntity toEntity(RoleDTO dto, RoleEntity entity) {
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(dto, entity);
        return entity;
    }

    public RoleDTO toDTO(RoleEntity entity) {
        RoleDTO dto = modelMapper.map(entity, RoleDTO.class);
        return dto;
    }

    public List<RoleEntity> toEntityList(List<RoleDTO> fieldDTOs) {
        List<RoleEntity> entities = new ArrayList<>();

        fieldDTOs.forEach((fieldDTO -> {
            entities.add(modelMapper.map(fieldDTO, RoleEntity.class));
        }));

        return entities;
    }

    public List<RoleDTO> toDTOList(List<RoleEntity> roleEntities) {
        List<RoleDTO> dtos = new ArrayList<>();

        roleEntities.forEach(entity -> {
            dtos.add(modelMapper.map(entity, RoleDTO.class));
        });

        return dtos;
    }
}
