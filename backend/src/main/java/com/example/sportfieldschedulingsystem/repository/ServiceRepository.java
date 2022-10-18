package com.example.sportfieldschedulingsystem.repository;

import com.example.sportfieldschedulingsystem.entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {
}
