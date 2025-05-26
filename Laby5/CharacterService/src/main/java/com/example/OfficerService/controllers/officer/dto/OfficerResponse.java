package com.example.OfficerService.controllers.officer.dto;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class OfficerResponse {
    UUID id;
    String name;
    int badgeNumber;
    int yearsOfService;
    int rank;
    UUID departmentId;
}