package com.example.sportfieldschedulingsystem.service;

import com.example.sportfieldschedulingsystem.dto.ScheduleDTO;

import java.util.List;

public interface ScheduleService extends BaseService<ScheduleDTO> {

    List<ScheduleDTO> findAllByUserID(Long userid);

}
