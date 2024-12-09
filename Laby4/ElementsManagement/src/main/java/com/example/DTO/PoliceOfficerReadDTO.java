package com.example.DTO;

import com.example.model.PoliceOfficer;
import lombok.*;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class PoliceOfficerReadDTO {
    private final UUID id;
    private final String name;
    private final int badgeNumber;
    private final int yearsOfService;
    private final int rank;
    private UUID department;

    public PoliceOfficerReadDTO(PoliceOfficer officer) {
        this.id = officer.getId();
        this.name = officer.getName();
        this.badgeNumber = officer.getBadgeNumber();
        this.yearsOfService = officer.getYearsOfService();
        this.rank = officer.getRank();
        this.department = officer.getDepartmentId();
    }
}
