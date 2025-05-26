package com.example.OfficerService.controllers.officer.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateOfficerRequest {
    String name;
    int badgeNumber;
    int yearsOfService;
    int rank;
}