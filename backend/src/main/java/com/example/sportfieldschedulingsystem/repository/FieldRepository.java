package com.example.sportfieldschedulingsystem.repository;

import com.example.sportfieldschedulingsystem.entity.FieldEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FieldRepository extends JpaRepository<FieldEntity, Long> {

    List<FieldEntity> findByFieldNameContainingOrAddressContaining(String fieldName, String address, Pageable pageable);

    @Query("select f from FieldEntity f " +
            "where (f.fieldName like concat('%', ?1, '%') or f.address like concat('%', ?2, '%')) and f.type = ?3")
    List<FieldEntity> findByFieldNameContainingOrAddressContainingAndType(String fieldName, String address, String type, Pageable pageable);

    List<FieldEntity> findByType(String type, Pageable pageable);

    int countByType(String type);

    int countByFieldNameContainingOrAddressContaining(String fieldName, String address);

    @Query("select count(f) from FieldEntity f " +
            "where (f.fieldName like concat('%', ?1, '%') or f.address like concat('%', ?2, '%')) and f.type = ?3")
    int countByFieldNameContainingOrAddressContainingAndType(String fieldName, String address, String type);
}
