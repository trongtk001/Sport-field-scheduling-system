package com.example.sportfieldschedulingsystem.repository;

import com.example.sportfieldschedulingsystem.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {


    Optional<UserEntity> findOneByUserName(String userName);
}
