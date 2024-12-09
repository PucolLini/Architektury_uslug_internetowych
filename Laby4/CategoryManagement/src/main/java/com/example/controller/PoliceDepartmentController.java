package com.example.controller;

import com.example.DTO.PoliceDepartmentCreateUpdateDTO;
import com.example.DTO.PoliceDepartmentDTO;
import com.example.DTO.PoliceDepartmentReadDTO;
import com.example.model.PoliceDepartment;
import com.example.service.PoliceDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/departments")
public class PoliceDepartmentController {
    @Autowired
    private final PoliceDepartmentService departmentService;

    @Autowired
    public PoliceDepartmentController(PoliceDepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<PoliceDepartmentReadDTO> createDepartment(@RequestBody PoliceDepartmentCreateUpdateDTO departmentDTO) {
        PoliceDepartment department = new PoliceDepartment();
        department.setName(departmentDTO.getName());
        department.setCity(departmentDTO.getCity());
        department.setNumberOfCases(departmentDTO.getNumberOfCases());

        PoliceDepartment savedDepartment = departmentService.saveDepartment(department);

        PoliceDepartmentReadDTO departmentReadDTO = new PoliceDepartmentReadDTO(savedDepartment);
        return new ResponseEntity<>(departmentReadDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PoliceDepartmentReadDTO> getDepartmentById(@PathVariable UUID id) {
        PoliceDepartment department = departmentService.getDepartmentById(id);
        if (department == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        PoliceDepartmentReadDTO departmentDTO = new PoliceDepartmentReadDTO(department);
        return ResponseEntity.ok(departmentDTO);
    }
//    public ResponseEntity<PoliceDepartmentReadDTO> getDepartmentById(@PathVariable UUID id) {
//        PoliceDepartment department = departmentService.getDepartmentById(id);
//        if (department == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        PoliceDepartmentReadDTO departmentReadDTO = new PoliceDepartmentReadDTO(department);
//        return new ResponseEntity<>(departmentReadDTO, HttpStatus.OK);


    // Get all departments
    @GetMapping
    public ResponseEntity<List<PoliceDepartmentDTO>> getAllDepartments() {
        List<PoliceDepartment> departments = departmentService.getAllDepartments();
        List<PoliceDepartmentDTO> departmentDTOs = departments.stream()
                .map(PoliceDepartmentDTO::new)
                .collect(Collectors.toList());
        return new ResponseEntity<>(departmentDTOs, HttpStatus.OK);
    }

    // Get departments by city
    @GetMapping("/city/{city}")
    public ResponseEntity<List<PoliceDepartmentDTO>> getDepartmentsByCity(@PathVariable String city) {
        List<PoliceDepartment> departments = departmentService.getDepartmentsByCity(city);
        if (departments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<PoliceDepartmentDTO> departmentDTOs = departments.stream()
                .map(PoliceDepartmentDTO::new)
                .collect(Collectors.toList());
        return new ResponseEntity<>(departmentDTOs, HttpStatus.OK);
    }

    // Update an existing department
    @PutMapping("/{id}")
    public ResponseEntity<PoliceDepartmentReadDTO> updateDepartment(@PathVariable UUID id,
                                                                    @RequestBody PoliceDepartmentCreateUpdateDTO departmentDTO) {
        PoliceDepartment existingDepartment = departmentService.getDepartmentById(id);
        if (existingDepartment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Update fields with values from DTO
        existingDepartment.setName(departmentDTO.getName());
        existingDepartment.setCity(departmentDTO.getCity());
        existingDepartment.setNumberOfCases(departmentDTO.getNumberOfCases());

        // Save the updated department
        PoliceDepartment updatedDepartment = departmentService.saveDepartment(existingDepartment);

        PoliceDepartmentReadDTO departmentReadDTO = new PoliceDepartmentReadDTO(updatedDepartment);
        return new ResponseEntity<>(departmentReadDTO, HttpStatus.OK);
    }

    // Update departments by city (Update all departments in the given city)
    @PutMapping("/city/{city}")
    public ResponseEntity<List<PoliceDepartmentReadDTO>> updateDepartmentsByCity(@PathVariable String city,
                                                                                 @RequestBody PoliceDepartmentCreateUpdateDTO departmentDTO) {
        List<PoliceDepartment> departments = departmentService.getDepartmentsByCity(city);
        if (departments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // No departments found for the city
        }

        for (PoliceDepartment department : departments) {
            department.setName(departmentDTO.getName());
            department.setCity(departmentDTO.getCity());
            department.setNumberOfCases(departmentDTO.getNumberOfCases());
            departmentService.saveDepartment(department);  // Update each department
        }

        List<PoliceDepartmentReadDTO> departmentReadDTOs = departments.stream()
                .map(PoliceDepartmentReadDTO::new)
                .collect(Collectors.toList());

        return new ResponseEntity<>(departmentReadDTOs, HttpStatus.OK);
    }

    // Delete a department by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable UUID id) {
        PoliceDepartment department = departmentService.getDepartmentById(id);
        if (department == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        departmentService.deleteDepartment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Delete departments by city
    @DeleteMapping("/city/{city}")
    public ResponseEntity<Void> deleteDepartmentsByCity(@PathVariable String city) {
        List<PoliceDepartment> departments = departmentService.getDepartmentsByCity(city);
        if (departments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // No departments found for the city
        }

        departmentService.deleteDepartmentsByCity(city);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
