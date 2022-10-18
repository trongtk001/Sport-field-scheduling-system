package com.example.sportfieldschedulingsystem.repository;

import com.example.sportfieldschedulingsystem.entity.FieldEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FieldRepository extends JpaRepository<FieldEntity, Long> {

    Page<FieldEntity> findByFieldNameContainingOrAddressContaining(String fieldName, String address, Pageable pageable);

    @Query("select f from FieldEntity f " +
            "where (f.fieldName like concat('%', ?1, '%') or f.address like concat('%', ?2, '%')) and f.type = ?3")
    Page<FieldEntity> findByFieldNameContainingOrAddressContainingAndType(String fieldName, String address, String type, Pageable pageable);

    @Query("select f from FieldEntity f " +
            "where (f.fieldName like concat('%', ?1, '%') or f.address like concat('%', ?2, '%')) and f.type = ?3 and f.status = ?4")
    Page<FieldEntity> findByFieldNameContainingOrAddressContainingAndTypeAndStatus(String fieldName, String address, String type, boolean status, Pageable pageable);

    Page<FieldEntity> findByType(String type, Pageable pageable);

    Page<FieldEntity> findAllByStatus(boolean status, Pageable pageable);
}
