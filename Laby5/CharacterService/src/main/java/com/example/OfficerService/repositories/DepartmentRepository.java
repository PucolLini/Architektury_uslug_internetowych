package com.example.OfficerService.repositories;

import com.example.OfficerService.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DepartmentRepository extends JpaRepository<Department, UUID> {
    List<Department> findAll();
    Optional<Department> findById(UUID id);
}
