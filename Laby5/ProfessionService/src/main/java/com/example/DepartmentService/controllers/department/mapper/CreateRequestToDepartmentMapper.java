package com.example.DepartmentService.controllers.department.mapper;

import com.example.DepartmentService.controllers.department.dto.CreateDepartmentRequest;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import com.example.DepartmentService.entity.Department;

@Component
public class CreateRequestToDepartmentMapper implements Function<CreateDepartmentRequest, Department> {
    @Override
    public Department apply(CreateDepartmentRequest createDepartmentRequest) {
        return Department.builder()
                .name(createDepartmentRequest.getName())
                .city(createDepartmentRequest.getCity())
                .numberOfCases(createDepartmentRequest.getNumberOfCases())
                .build();
    }
}
