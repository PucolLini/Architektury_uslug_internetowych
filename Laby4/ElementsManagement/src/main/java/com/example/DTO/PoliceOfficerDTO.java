package com.example.DTO;

import com.example.model.PoliceOfficer;
import lombok.Data;

import java.util.UUID;

@Data
public class PoliceOfficerDTO {
    private String name;
    private int badgeNumber;
    private UUID department;

    public PoliceOfficerDTO(PoliceOfficer officer) {
        this.name = officer.getName();
        this.badgeNumber = officer.getBadgeNumber();
        this.department = officer.getDepartmentId();
    }
}
