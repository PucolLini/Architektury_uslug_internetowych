package com.example.DepartmentService.controllers.department.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateDepartmentRequest {
    String name;
    String city;
    int numberOfCases;
}
