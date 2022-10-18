package com.example.sportfieldschedulingsystem.mapper;

import com.example.sportfieldschedulingsystem.dto.ScheduleDetailDTO;
import com.example.sportfieldschedulingsystem.entity.FieldEntity;
import com.example.sportfieldschedulingsystem.entity.ScheduleDetailEntity;
import com.example.sportfieldschedulingsystem.entity.ScheduleEntity;
import com.example.sportfieldschedulingsystem.entity.ServiceEntity;
import com.example.sportfieldschedulingsystem.repository.FieldRepository;
import com.example.sportfieldschedulingsystem.repository.ServiceRepository;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ScheduleDetailMapper {

    private final ModelMapper modelMapper;

    private final FieldRepository fieldRepository;

    private final ServiceRepository serviceRepository;

    public ScheduleDetailMapper(ModelMapper modelMapper, FieldRepository fieldRepository, ServiceRepository serviceRepository) {
        this.modelMapper = modelMapper;
        this.fieldRepository = fieldRepository;
        this.serviceRepository = serviceRepository;
    }

    public ScheduleDetailEntity toEntity(ScheduleDetailDTO dto) {
        ScheduleDetailEntity entity = modelMapper.map(dto, ScheduleDetailEntity.class);
        entity.setScheduleEntity(new ScheduleEntity(dto.getScheduleID()));
        if (dto.getFieldId() != null) {
            entity.setFieldEntity(new FieldEntity(dto.getFieldId()));
        }
        if (dto.getServiceIDs() != null) {
            entity.setServiceEntities(dto.getServiceIDs().stream().map(id -> new ServiceEntity(id)).collect(Collectors.toList()));
        }
        return entity;
    }


    public ScheduleDetailEntity toEntity(ScheduleDetailDTO dto, ScheduleDetailEntity entity) {
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(dto, entity);
        if (dto.getServiceIDs() != null) {
            entity.setServiceEntities(dto.getServiceIDs().stream().map(id -> new ServiceEntity(id)).collect(Collectors.toList()));
        }
        return entity;
    }

    public ScheduleDetailDTO toDTO(ScheduleDetailEntity entity) {
        ScheduleDetailDTO dto = modelMapper.map(entity, ScheduleDetailDTO.class);
        dto.setScheduleID(entity.getScheduleEntity().getId());
        dto.setFieldId(entity.getFieldEntity().getId());
        dto.setServiceIDs(entity.getServiceEntities().stream().map(service -> service.getId()).collect(Collectors.toList()));
        return dto;
    }

    public List<ScheduleDetailEntity> toEntityList(List<ScheduleDetailDTO> fieldDTOS) {
        List<ScheduleDetailEntity> entities = fieldDTOS.stream().map(dto -> toEntity(dto)).collect(Collectors.toList());

        return entities;
    }

    public List<ScheduleDetailDTO> toDTOList(List<ScheduleDetailEntity> fieldEntities) {
        List<ScheduleDetailDTO> dtos = fieldEntities.stream().map(entity -> toDTO(entity)).collect(Collectors.toList());

        return dtos;
    }
}
