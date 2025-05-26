package com.example.DepartmentService.controllers.department.mapper;

import com.example.DepartmentService.controllers.department.dto.DepartmentsResponse;
import com.example.DepartmentService.entity.Department;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
@Component
public class DepartmentsToResponseMapper implements Function<List<Department>, DepartmentsResponse>{
    @Override
    public DepartmentsResponse apply(List<Department> departments) {
        return DepartmentsResponse.builder()
                .departments(departments.stream()
                        .map(department -> DepartmentsResponse.Department.builder()
                                .id(department.getId())
                                .name(department.getName())
                                .city(department.getCity())
                                .build())
                        .toList())
                .build();
    }
}
