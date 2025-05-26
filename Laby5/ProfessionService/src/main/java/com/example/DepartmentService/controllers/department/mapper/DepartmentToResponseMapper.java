package com.example.DepartmentService.controllers.department.mapper;

import com.example.DepartmentService.controllers.department.dto.DepartmentResponse;
import com.example.DepartmentService.entity.Department;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class DepartmentToResponseMapper implements Function<Department, DepartmentResponse>{
    @Override
    public DepartmentResponse apply(Department department) {
        return DepartmentResponse.builder()
                .id(department.getId())
                .name(department.getName())
                .city(department.getCity())
                .numberOfCases(department.getNumberOfCases())
                .build();
    }
}
