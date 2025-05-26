package com.example.OfficerService.controllers.officer.dto;

import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.UUID;

@Value
@Builder
public class OfficersResponse {
    List<Officer> officers;

    @Value
    @Builder
    public static class Officer {
        UUID id;
        String name;
    }
}