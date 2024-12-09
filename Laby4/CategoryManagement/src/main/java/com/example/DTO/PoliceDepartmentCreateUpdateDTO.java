package com.example.DTO;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class PoliceDepartmentCreateUpdateDTO {

    @NotBlank(message = "Department name cannot be blank")
    @Size(max = 50, message = "Department name cannot exceed 50 characters")
    private String name;

    @NotBlank(message = "City cannot be blank")
    @Size(max = 50, message = "City cannot exceed 50 characters")
    private String city;

    @Min(value = 0, message = "Number of cases cannot be negative")
    private int numberOfCases;
}
