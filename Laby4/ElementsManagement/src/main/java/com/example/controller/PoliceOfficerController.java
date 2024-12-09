package com.example.controller;

import com.example.DTO.PoliceOfficerCreateUpdateDTO;
import com.example.DTO.PoliceOfficerDTO;
import com.example.DTO.PoliceOfficerReadDTO;
import com.example.model.PoliceOfficer;
import com.example.repository.PoliceOfficerRepository;
import com.example.service.PoliceOfficerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/officers")
public class PoliceOfficerController {

    private final PoliceOfficerService officerService;
    @Autowired
    public PoliceOfficerController(PoliceOfficerService officerService) {
        this.officerService = officerService;
    }

    @PostMapping
    public ResponseEntity<PoliceOfficerReadDTO> createOfficer(@RequestBody PoliceOfficerReadDTO officerDTO) {
        PoliceOfficer officer = new PoliceOfficer();
        officer.setName(officerDTO.getName());
        officer.setBadgeNumber(officerDTO.getBadgeNumber());
        officer.setYearsOfService(officerDTO.getYearsOfService());
        officer.setRank(officerDTO.getRank());

        PoliceOfficer savedOfficer = officerService.saveOfficer(officer);
        PoliceOfficerReadDTO officerReadDTO = new PoliceOfficerReadDTO(savedOfficer);
        return new ResponseEntity<>(officerReadDTO, HttpStatus.CREATED);
    }
//    @PostMapping
//    public ResponseEntity<PoliceOfficerReadDTO> createOfficer(@RequestBody PoliceOfficerCreateUpdateDTO officerDTO) {
//        // Convert DTO to entity
//        PoliceOfficer officer = new PoliceOfficer();
//        officer.setName(officerDTO.getName());
//        officer.setBadgeNumber(officerDTO.getBadgeNumber());
//        officer.setYearsOfService(officerDTO.getYearsOfService());
//        officer.setRank(officerDTO.getRank());
//
//        // Retrieve department by departmentId from the DTO
//        PoliceDepartment department = departmentService.getDepartmentById(officerDTO.getDepartmentId());
//        if (department == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 if the department is not found
//        }
//
//        officer.setDepartment(department); // Set department on the officer
//
//        // Save the officer using service
//        PoliceOfficer savedOfficer = officerService.saveOfficer(officer);
//
//        // Return response
//        PoliceOfficerReadDTO officerReadDTO = new PoliceOfficerReadDTO(savedOfficer);
//        return new ResponseEntity<>(officerReadDTO, HttpStatus.CREATED);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<PoliceOfficerReadDTO> getOfficerById(@PathVariable UUID id) {
        PoliceOfficer officer = officerService.getOfficerById(id);
        if (officer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        PoliceOfficerReadDTO officerReadDTO = new PoliceOfficerReadDTO(officer);
        return new ResponseEntity<>(officerReadDTO, HttpStatus.OK);
    }

    @GetMapping("/officers/by-badge/{badgeNumber}")
    public ResponseEntity<PoliceOfficerDTO> getOfficerByBadgeNumber(@PathVariable int badgeNumber) {
        PoliceOfficer officer = officerService.getOfficerByBadgeNumber(badgeNumber);
        if (officer != null) {
            return new ResponseEntity<>(new PoliceOfficerDTO(officer), HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Officer not found");
        }
    }

    @GetMapping
    public ResponseEntity<List<PoliceOfficerDTO>> getAllOfficers() {
        List<PoliceOfficer> officers = officerService.getAllOfficers();
        List<PoliceOfficerDTO> officerDTOs = officers.stream()
                .map(PoliceOfficerDTO::new) // Transform entities to DTOs
                .collect(Collectors.toList());
        return new ResponseEntity<>(officerDTOs, HttpStatus.OK);
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<PoliceOfficerDTO>> getOfficersByDepartment(@PathVariable UUID departmentId) {
        List<PoliceOfficer> officers = officerService.getOfficersByDepartment(departmentId);
        if (officers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<PoliceOfficerDTO> officerDTOs = officers.stream()
                .map(PoliceOfficerDTO::new)
                .collect(Collectors.toList());
        return new ResponseEntity<>(officerDTOs, HttpStatus.OK);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<PoliceOfficerReadDTO> updateOfficer(@PathVariable UUID id,
//                                                              @RequestBody PoliceOfficerCreateUpdateDTO officerDTO) {
//        PoliceOfficer existingOfficer = officerService.getOfficerById(id);
//        if (existingOfficer == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        // Update fields with values from DTO
//        existingOfficer.setName(officerDTO.getName());
//        existingOfficer.setBadgeNumber(officerDTO.getBadgeNumber());
//        existingOfficer.setYearsOfService(officerDTO.getYearsOfService());
//        existingOfficer.setRank(officerDTO.getRank());
//
//        // Retrieve department by departmentId from the DTO
//        PoliceDepartment department = departmentService.getDepartmentById(officerDTO.getDepartmentId());
//        if (department == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 if the department is not found
//        }
//
//        existingOfficer.setDepartment(department); // Set department on the officer
//
//        // Save the updated officer
//        PoliceOfficer updatedOfficer = officerService.saveOfficer(existingOfficer);
//
//        PoliceOfficerReadDTO officerReadDTO = new PoliceOfficerReadDTO(updatedOfficer);
//        return new ResponseEntity<>(officerReadDTO, HttpStatus.OK);
//    }

    // Delete an officer by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOfficer(@PathVariable UUID id) {
        PoliceOfficer officer = officerService.getOfficerById(id);
        if (officer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        officerService.deleteOfficer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Delete an officer by name
    @DeleteMapping("/name/{name}")
    public ResponseEntity<Void> deleteOfficerByName(@PathVariable String name) {
        boolean deleted = officerService.deleteOfficerByName(name);

        if (!deleted) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Officer with specified name not found
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // Successfully deleted
    }

}
