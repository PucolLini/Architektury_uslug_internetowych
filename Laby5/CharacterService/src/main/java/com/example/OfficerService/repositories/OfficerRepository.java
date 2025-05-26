package com.example.OfficerService.repositories;

import com.example.OfficerService.entities.Officer;
import com.example.OfficerService.entities.Department;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OfficerRepository extends JpaRepository<Officer, UUID> {
    List<Officer> findAll();

    Optional<Officer> findById(UUID id);
    List<Officer> findByDepartment(Department department);
    List<Officer> findByBadgeNumber(int badgeNumber);
    List<Officer> findByRank(int rank);
    List<Officer> findByYearsOfService(int yearsOfService);
    List<Officer> findByNameIgnoreCase(String name);
}
