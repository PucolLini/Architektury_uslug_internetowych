package com.example.DepartmentService.controllers.department.dto;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class DepartmentResponse {
    UUID id;
    String name;
    String city;
    int numberOfCases;
}
