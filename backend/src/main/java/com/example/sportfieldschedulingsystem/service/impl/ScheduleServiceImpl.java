package com.example.sportfieldschedulingsystem.service.impl;

import com.example.sportfieldschedulingsystem.dto.ScheduleDTO;
import com.example.sportfieldschedulingsystem.entity.ScheduleEntity;
import com.example.sportfieldschedulingsystem.entity.UserEntity;
import com.example.sportfieldschedulingsystem.mapper.ScheduleDetailMapper;
import com.example.sportfieldschedulingsystem.mapper.ScheduleMapper;
import com.example.sportfieldschedulingsystem.repository.ScheduleRepository;
import com.example.sportfieldschedulingsystem.service.ScheduleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    private final ScheduleMapper scheduleMapper;

    private final ScheduleDetailMapper scheduleDetailMapper;


    public ScheduleServiceImpl(ScheduleRepository scheduleRepository, ScheduleMapper scheduleMapper, ScheduleDetailMapper scheduleDetailMapper) {
        this.scheduleRepository = scheduleRepository;
        this.scheduleMapper = scheduleMapper;
        this.scheduleDetailMapper = scheduleDetailMapper;
    }

    @Override
    public ScheduleDTO save(ScheduleDTO dto) {
        ScheduleEntity entity;

        if (dto.getId() != null) {
            entity = scheduleRepository.findById(dto.getId()).orElse(null);
            if (entity != null) {
                entity = scheduleMapper.toEntity(dto, entity);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found this schedule");
            }
        } else {
            entity = scheduleMapper.toEntity(dto);
        }

        entity = scheduleRepository.save(entity);

        dto = scheduleMapper.toDTO(entity);

        return dto;
    }

    @Override
    public ScheduleDTO delete(long id) {
        ScheduleEntity entity = scheduleRepository.findById(id).orElse(null);
        if (entity != null) {
            scheduleRepository.delete(entity);
            return scheduleMapper.toDTO(entity);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found this schedule");
        }
    }

    @Override
    public ScheduleDTO search(Long id) {
        ScheduleEntity scheduleEntity = scheduleRepository.findById(id).orElse(null);
        ScheduleDTO scheduleDTO = scheduleMapper.toDTO(scheduleEntity);
        return scheduleDTO;
    }

    @Override
    public Page<ScheduleDTO> findAll(Pageable pageable) {
        Page<ScheduleEntity> scheduleEntities = scheduleRepository.findAll(pageable);

        Page<ScheduleDTO> scheduleDTOS = new PageImpl<>(scheduleMapper.toDTOList(scheduleEntities.getContent()), pageable, scheduleEntities.getTotalElements());

        return scheduleDTOS;
    }

    @Override
    public List<ScheduleDTO> findAllByUserID(Long userid) {
        List<ScheduleEntity> scheduleEntities = scheduleRepository.findAllByUserEntity(new UserEntity(userid));

        List<ScheduleDTO> scheduleDTOS = scheduleMapper.toDTOList(scheduleEntities);

        return scheduleDTOS;
    }
}
