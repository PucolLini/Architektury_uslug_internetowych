package com.example.OfficerService.controllers.officer.mapper;

import com.example.OfficerService.entities.Officer;
import org.springframework.stereotype.Component;
import java.util.function.Function;

import com.example.OfficerService.controllers.officer.dto.OfficerResponse;
@Component
public class OfficerToResponseMapper implements Function<Officer, OfficerResponse> {
    @Override
    public OfficerResponse apply(Officer officer) {
        return OfficerResponse.builder()
                .id(officer.getId())
                .name(officer.getName())
                .badgeNumber(officer.getBadgeNumber())
                .yearsOfService(officer.getYearsOfService())
                .rank(officer.getRank())
                .departmentId(officer.getDepartment().getId())
                .build();
    }
}
