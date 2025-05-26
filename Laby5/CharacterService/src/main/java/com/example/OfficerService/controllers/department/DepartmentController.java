package com.example.OfficerService.controllers.department;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;
public interface DepartmentController {

    @PutMapping("/departments/{uuid}")
    @ResponseStatus(HttpStatus.CREATED)
    void createDepartment(@PathVariable UUID uuid);

    @DeleteMapping("/departments/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteDepartment(@PathVariable UUID uuid);
}
