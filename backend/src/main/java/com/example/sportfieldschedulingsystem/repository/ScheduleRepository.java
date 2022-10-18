package com.example.sportfieldschedulingsystem.repository;

import com.example.sportfieldschedulingsystem.entity.FieldEntity;
import com.example.sportfieldschedulingsystem.entity.ScheduleEntity;
import com.example.sportfieldschedulingsystem.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {

    List<ScheduleEntity> findAllByUserEntity(UserEntity user);

}
