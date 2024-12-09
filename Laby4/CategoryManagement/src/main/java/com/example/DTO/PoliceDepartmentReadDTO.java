package com.example.DTO;

import com.example.model.PoliceDepartment;
import lombok.*;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class PoliceDepartmentReadDTO {
    private final UUID id;
    private final String name;
    private final String city;
    private final int numberOfCases;

    public PoliceDepartmentReadDTO(PoliceDepartment department) {
        this.id = department.getId();
        this.name = department.getName();
        this.city = department.getCity();
        this.numberOfCases = department.getNumberOfCases();
    }
}
