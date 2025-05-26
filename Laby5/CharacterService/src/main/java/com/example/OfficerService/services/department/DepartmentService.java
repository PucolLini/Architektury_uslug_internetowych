package com.example.OfficerService.services.department;

import com.example.OfficerService.entities.Department;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DepartmentService {
    List<Department> findAll();
    Optional<Department> findById(UUID id);
    void create(Department department);
    void update(Department department);
    void delete(UUID id);
}
