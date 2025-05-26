package com.example.OfficerService.services.officer;

import com.example.OfficerService.entities.Officer;
import com.example.OfficerService.repositories.OfficerRepository;
import com.example.OfficerService.entities.Department;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Transactional // When using database
@Service
public class OfficerServiceImplementation implements OfficerService {

    private final OfficerRepository officerRepository;

    @Autowired
    public OfficerServiceImplementation(OfficerRepository officerRepository) {
        this.officerRepository = officerRepository;
    }
    @Override
    public List<Officer> findAll() {
        return officerRepository.findAll();
    }

    @Override
    public Optional<Officer> findById(UUID id) {
        return officerRepository.findById(id);
    }

    @Override
    public List<Officer> findByDepartment(Department department) {
        return officerRepository.findByDepartment(department);
    }

    @Override
    public List<Officer> findByRank(int rank) {
        return officerRepository.findByRank(rank);
    }

    @Override
    public List<Officer> findByBadgeNumber(int badgeNumber) {
        return officerRepository.findByYearsOfService(badgeNumber);
    }

    @Override
    public List<Officer> findByYearsOfService(int yearsOfService) {
        return officerRepository.findByYearsOfService(yearsOfService);
    }

    @Override
    public List<Officer> findByNameIgnoreCase(String name) {
        return officerRepository.findByNameIgnoreCase(name);
    }

    @Override
    public void create(Officer officer) {
        officerRepository.save(officer);
    }

    @Override
    public void update(Officer officer) {
        officerRepository.save(officer);
    }

    @Override
    public void delete(UUID officerId) {
        officerRepository.deleteById(officerId);
    }

}
