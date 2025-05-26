package com.example.DepartmentService.controllers.department.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UpdateDepartmentRequest {
    String name;
    String city;
    Integer numberOfCases;
}
