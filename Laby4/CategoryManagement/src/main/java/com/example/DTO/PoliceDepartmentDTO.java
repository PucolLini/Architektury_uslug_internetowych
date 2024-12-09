package com.example.DTO;

import com.example.model.PoliceDepartment;
import lombok.*;

@Data
public class PoliceDepartmentDTO {
    private String name;
    private String city;

    public PoliceDepartmentDTO(PoliceDepartment department) {
        this.name = department.getName();
        this.city = department.getCity();
    }
}
