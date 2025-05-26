package com.example.DepartmentService.service.department;

import com.example.DepartmentService.entity.Department;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DepartmentService {
    List<Department> findAll();
    List<Department> findByNameIgnoreCase(String name);
    Optional<Department> findById(UUID id);
    void create(Department department);
    void update(Department department);
    void delete(UUID id);
}
