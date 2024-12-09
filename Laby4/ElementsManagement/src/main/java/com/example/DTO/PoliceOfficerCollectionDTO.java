package com.example.DTO;

import com.example.model.PoliceOfficer;
import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class PoliceOfficerCollectionDTO {
    private UUID id;
    private String name;
    private int badgeNumber;

    public PoliceOfficerCollectionDTO(PoliceOfficer officer) {
        this.id = officer.getId();
        this.name = officer.getName();
        this.badgeNumber = officer.getBadgeNumber();
    }
}
