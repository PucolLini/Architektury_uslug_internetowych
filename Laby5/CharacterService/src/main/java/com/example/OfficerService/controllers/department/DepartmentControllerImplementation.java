package com.example.OfficerService.controllers.department;

import com.example.OfficerService.entities.Department;
import com.example.OfficerService.services.department.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;
@RestController
public class DepartmentControllerImplementation implements DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentControllerImplementation(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Override
    public void createDepartment(UUID uuid) {
        if(uuid == null)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "UUID can't be NULL!");
        }

        if(departmentService.findById(uuid).isPresent())
        {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Department already exists!");
        }

        departmentService.create(Department.builder().id(uuid).build());
    }

    @Override
    public void deleteDepartment(UUID uuid) {
        if(departmentService.findById(uuid).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Department doesn't exists!");
        }

        departmentService.delete(uuid);
    }
}
