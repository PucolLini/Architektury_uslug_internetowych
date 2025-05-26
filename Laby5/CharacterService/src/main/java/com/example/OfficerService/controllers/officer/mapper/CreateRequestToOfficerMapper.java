package com.example.OfficerService.controllers.officer.mapper;


import com.example.OfficerService.entities.Officer;
import org.springframework.stereotype.Component;
import java.util.function.Function;

import com.example.OfficerService.controllers.officer.dto.CreateOfficerRequest;

@Component
public class CreateRequestToOfficerMapper implements Function<CreateOfficerRequest, Officer> {
    @Override
    public Officer apply(CreateOfficerRequest createOfficerRequest) {
        return Officer.builder()
                .name(createOfficerRequest.getName())
                .badgeNumber(createOfficerRequest.getBadgeNumber())
                .yearsOfService(createOfficerRequest.getYearsOfService())
                .rank(createOfficerRequest.getRank())
                .build();
    }
}