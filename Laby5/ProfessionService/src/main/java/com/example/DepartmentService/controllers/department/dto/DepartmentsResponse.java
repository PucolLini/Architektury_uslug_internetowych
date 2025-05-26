package com.example.DepartmentService.controllers.department.dto;

import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.UUID;

@Value
@Builder
public class DepartmentsResponse {
    List<Department> departments;

    @Value
    @Builder
    public static class Department {
        UUID id;
        String name;
        String city;
    }
}