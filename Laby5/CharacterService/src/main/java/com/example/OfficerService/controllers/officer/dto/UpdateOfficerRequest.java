package com.example.OfficerService.controllers.officer.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UpdateOfficerRequest {
    String name;
    Integer badgeNumber;
    Integer yearsOfService;
    Integer rank;
}