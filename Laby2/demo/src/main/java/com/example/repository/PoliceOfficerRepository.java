package com.example.repository;

import com.example.model.PoliceOfficer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PoliceOfficerRepository extends JpaRepository<PoliceOfficer, UUID> {
    List<PoliceOfficer> findByDepartmentId(UUID departmentId);
    List<PoliceOfficer> findByRank(int rank);
}
