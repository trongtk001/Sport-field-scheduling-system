package com.example.sportfieldschedulingsystem.repository;

import com.example.sportfieldschedulingsystem.entity.FeedbackEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<FeedbackEntity, Long> {
}
