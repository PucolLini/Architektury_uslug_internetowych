package com.example.OfficerService.services.officer;

import com.example.OfficerService.entities.Officer;
import com.example.OfficerService.entities.Department;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OfficerService {
    List<Officer> findAll();
    Optional<Officer> findById(UUID id);
    List<Officer> findByDepartment(Department department);
    List<Officer> findByRank(int rank);
    List<Officer> findByBadgeNumber(int badgeNumber);
    List<Officer> findByYearsOfService(int yearsOfService);

    List<Officer> findByNameIgnoreCase(String name);

    void create(Officer officer);
    void update(Officer officer);
    void delete(UUID officerId);
}
