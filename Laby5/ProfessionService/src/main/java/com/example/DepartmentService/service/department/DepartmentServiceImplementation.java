package com.example.DepartmentService.service.department;

import com.example.DepartmentService.entity.Department;
import com.example.DepartmentService.repositories.event.EventRepository;
import com.example.DepartmentService.repositories.department.DepartmentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Transactional
@Service
public class DepartmentServiceImplementation implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final EventRepository eventRepository;

    @Autowired
    public DepartmentServiceImplementation(DepartmentRepository departmentRepository, EventRepository eventRepository) {
        this.departmentRepository = departmentRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public List<Department> findByNameIgnoreCase(String name) {
        return departmentRepository.findByNameIgnoreCase(name);
    }

    @Override
    public Optional<Department> findById(UUID id) {
        return departmentRepository.findById(id);
    }

    @Override
    public void create(Department department) {
        departmentRepository.save(department);
        eventRepository.sendCreateDepartmentEvent(department.getId());
    }

    @Override
    public void update(Department department) {
        departmentRepository.save(department);
    }

    @Override
    public void delete(UUID id) {
        departmentRepository.deleteById(id);
        eventRepository.sendDeleteDepartmentEvent(id);
    }


}
