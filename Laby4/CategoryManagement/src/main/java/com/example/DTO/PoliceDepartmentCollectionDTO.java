package com.example.DTO;

import com.example.model.PoliceDepartment;
import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class PoliceDepartmentCollectionDTO {
    private UUID id;
    private String name;
    private String city;

    public PoliceDepartmentCollectionDTO(PoliceDepartment department) {
        this.id = department.getId();
        this.name = department.getName();
        this.city = department.getCity();
    }
}
