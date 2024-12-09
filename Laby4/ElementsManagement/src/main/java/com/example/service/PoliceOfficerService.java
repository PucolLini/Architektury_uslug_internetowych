package com.example.service;

import com.example.model.PoliceOfficer;
import com.example.repository.PoliceOfficerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    //
    public PoliceOfficer getOfficerByBadgeNumber(int badgeNumber) {
        List<PoliceOfficer> officers = officerRepository.findByBadgeNumber(badgeNumber);
        if (officers.isEmpty()) {
            return null;  // Jeśli lista jest pusta, nie znaleziono oficera
        } else if (officers.size() > 1) {
            // Jeśli lista zawiera więcej niż 1 element, możesz rzucić wyjątek, jeśli chcesz, aby numery odznak były unikalne
            throw new IllegalStateException("Multiple officers found with the same badge number");
        }
        return officers.get(0);  // Zwróć pierwszego oficera, jeśli tylko jeden istnieje
    }

    public boolean deleteOfficerByName(String name) {
        List<PoliceOfficer> officers = officerRepository.findByName(name);

        if (officers.isEmpty()) {
            return false;  // No officer found with the given name
        }

        officerRepository.deleteAll(officers);  // Delete all officers with the given name
        return true;
    }
}
