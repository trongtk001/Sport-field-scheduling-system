package com.example.sportfieldschedulingsystem.repository;


import com.example.sportfieldschedulingsystem.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    RoleEntity findOneByRoleName(String roleName);
}
