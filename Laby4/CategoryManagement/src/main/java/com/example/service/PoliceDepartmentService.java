package com.example.service;

import com.example.model.PoliceDepartment;
import com.example.repository.PoliceDepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PoliceDepartmentService {
    @Autowired
    public final PoliceDepartmentRepository departmentRepository;

    @Autowired
    public PoliceDepartmentService(PoliceDepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<PoliceDepartment> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public PoliceDepartment getDepartmentById(UUID id) {
        return departmentRepository.findById(id).orElse(null);
    }

    public List<PoliceDepartment> getDepartmentsByCity(String city) {
        return departmentRepository.findByCity(city);
    }

    public PoliceDepartment saveDepartment(PoliceDepartment department) {
        return departmentRepository.save(department);
    }

    public void deleteDepartment(UUID id) {
        departmentRepository.deleteById(id);
    }

    public void deleteDepartmentsByCity(String city) {
        List<PoliceDepartment> departments = getDepartmentsByCity(city);
        if (!departments.isEmpty()) {
            departmentRepository.deleteAll(departments);
        }
    }
}
