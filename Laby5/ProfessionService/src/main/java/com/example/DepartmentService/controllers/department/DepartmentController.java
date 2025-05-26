package com.example.DepartmentService.controllers.department;

import com.example.DepartmentService.controllers.department.dto.CreateDepartmentRequest;
import com.example.DepartmentService.controllers.department.dto.DepartmentsResponse;
import com.example.DepartmentService.controllers.department.dto.UpdateDepartmentRequest;
import com.example.DepartmentService.controllers.department.dto.DepartmentResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
public interface DepartmentController {
    @GetMapping("/departments")
    @ResponseStatus(HttpStatus.OK)
    DepartmentsResponse getDepartments();

    @GetMapping("/departments/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    DepartmentResponse getDepartment(@PathVariable UUID uuid);

    @PutMapping("/departments") // PUT here, not POST because if does exist, updates it and all info about it
    @ResponseStatus(HttpStatus.CREATED)
    DepartmentResponse createDepartment(@RequestBody CreateDepartmentRequest request);

    @PatchMapping("/departments/{uuid}")    // PATCH here, not PUT because it demands that object exists (in content just new values, just a delta of the info stored here)
    @ResponseStatus(HttpStatus.OK)
    DepartmentResponse updateDepartment(@PathVariable UUID uuid, @RequestBody UpdateDepartmentRequest request);

    @DeleteMapping("/departments/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteDepartment(@PathVariable UUID uuid);
}
