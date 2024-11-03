package com.example.service;

import com.example.model.PoliceOfficer;
import com.example.repository.PoliceOfficerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PoliceOfficerService {

    private final PoliceOfficerRepository officerRepository;

    @Autowired
    public PoliceOfficerService(PoliceOfficerRepository officerRepository) {
        this.officerRepository = officerRepository;
    }

    public List<PoliceOfficer> getAllOfficers() {
        return officerRepository.findAll();
    }

    public PoliceOfficer getOfficerById(UUID id) {
        return officerRepository.findById(id).orElse(null);
    }

    public List<PoliceOfficer> getOfficersByDepartment(UUID departmentId) {
        return officerRepository.findByDepartmentId(departmentId);
    }

    public List<PoliceOfficer> getOfficersByRank(int rank) {
        return officerRepository.findByRank(rank);
    }

    public PoliceOfficer saveOfficer(PoliceOfficer officer) {
        return officerRepository.save(officer);
    }

    public void deleteOfficer(UUID id) {
        officerRepository.deleteById(id);
    }
}
