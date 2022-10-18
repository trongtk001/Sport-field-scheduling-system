package com.example.sportfieldschedulingsystem.mapper;

import com.example.sportfieldschedulingsystem.dto.ScheduleDTO;
import com.example.sportfieldschedulingsystem.entity.ScheduleEntity;
import com.example.sportfieldschedulingsystem.repository.UserRepository;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ScheduleMapper {

    private final ModelMapper modelMapper;

    private final UserRepository userRepository;

    private final ScheduleDetailMapper scheduleDetailMapper;

    public ScheduleMapper(ModelMapper modelMapper, UserRepository userRepository, ScheduleDetailMapper scheduleDetailMapper) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.scheduleDetailMapper = scheduleDetailMapper;
    }

    public ScheduleEntity toEntity(ScheduleDTO dto) {
        ScheduleEntity entity = modelMapper.map(dto, ScheduleEntity.class);
        entity.setUserEntity(userRepository.findOneByUserName(dto.getUserName()).orElse(null));
        return entity;
    }

    public ScheduleEntity toEntity(ScheduleDTO dto, ScheduleEntity entity) {
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(dto, entity);
        return entity;
    }

    public ScheduleDTO toDTO(ScheduleEntity entity) {
        ScheduleDTO dto = modelMapper.map(entity, ScheduleDTO.class);
        dto.setUserName(entity.getUserEntity().getUserName());
        dto.setScheduleDetailDTOS(entity.getScheduleDetailEntities().stream().map(scheduleDetailEntity -> scheduleDetailMapper.toDTO(scheduleDetailEntity)).collect(Collectors.toList()));
        return dto;
    }

    public List<ScheduleEntity> toEntityList(List<ScheduleDTO> fieldDTOS) {
        List<ScheduleEntity> entities = new ArrayList<>();

        fieldDTOS.forEach((dto -> {
            entities.add(toEntity(dto));
        }));

        return entities;
    }

    public List<ScheduleDTO> toDTOList(List<ScheduleEntity> fieldEntities) {
        List<ScheduleDTO> dtos = new ArrayList<>();

        fieldEntities.forEach(entity -> {
            dtos.add(toDTO(entity));
        });

        return dtos;
    }
}
