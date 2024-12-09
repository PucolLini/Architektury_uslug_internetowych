package com.example.repository;

import com.example.model.PoliceDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PoliceDepartmentRepository extends JpaRepository<PoliceDepartment, UUID> {
    List<PoliceDepartment> findByCity(String city);
}
