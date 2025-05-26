package com.example.DepartmentService.repositories.department;

import com.example.DepartmentService.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DepartmentRepository extends JpaRepository<Department, UUID> {
    List<Department> findAll();
    List<Department> findByNameIgnoreCase(String name);

    Optional<Department> findById(UUID id);
}
