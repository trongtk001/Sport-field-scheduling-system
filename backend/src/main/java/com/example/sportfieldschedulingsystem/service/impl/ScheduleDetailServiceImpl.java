package com.example.sportfieldschedulingsystem.service.impl;

import com.example.sportfieldschedulingsystem.dto.ScheduleDetailDTO;
import com.example.sportfieldschedulingsystem.entity.FieldEntity;
import com.example.sportfieldschedulingsystem.entity.ScheduleDetailEntity;
import com.example.sportfieldschedulingsystem.entity.ServiceEntity;
import com.example.sportfieldschedulingsystem.mapper.ScheduleDetailMapper;
import com.example.sportfieldschedulingsystem.repository.FieldRepository;
import com.example.sportfieldschedulingsystem.repository.ScheduleDetailRepository;
import com.example.sportfieldschedulingsystem.repository.ServiceRepository;
import com.example.sportfieldschedulingsystem.service.ScheduleDetailService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleDetailServiceImpl implements ScheduleDetailService {

    private final ScheduleDetailRepository scheduleDetailRepository;

    private final FieldRepository fieldRepository;

    private final ServiceRepository serviceRepository;

    private final ScheduleDetailMapper scheduleDetailMapper;

    public ScheduleDetailServiceImpl(ScheduleDetailRepository scheduleDetailRepository, FieldRepository fieldRepository, ServiceRepository serviceRepository, ScheduleDetailMapper scheduleDetailMapper) {
        this.scheduleDetailRepository = scheduleDetailRepository;
        this.fieldRepository = fieldRepository;
        this.serviceRepository = serviceRepository;
        this.scheduleDetailMapper = scheduleDetailMapper;
    }

    @Override
    public ScheduleDetailDTO save(ScheduleDetailDTO dto) {
        ScheduleDetailEntity entity;

        if(dto.getId() != null) {
            entity = scheduleDetailRepository.findById(dto.getId()).orElse(null);
            if (entity != null) {
                entity = scheduleDetailMapper.toEntity(dto, entity);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found this schedule");
            }
        } else {
            entity = scheduleDetailMapper.toEntity(dto);
        }

        entity = scheduleDetailRepository.save(entity);

        FieldEntity field = fieldRepository.findById(entity.getFieldEntity().getId()).orElse(entity.getFieldEntity());
        field.setStatus(true);
        fieldRepository.save(field);

        return scheduleDetailMapper.toDTO(entity);
    }

    @Override
    public ScheduleDetailDTO delete(long id) {
        return null;
    }

    @Override
    public ScheduleDetailDTO search(Long id) {
        return null;
    }

    @Override
    public Page<ScheduleDetailDTO> findAll(Pageable pageable) {
        return null;
    }

    public void schedule(int time) {

    }

}