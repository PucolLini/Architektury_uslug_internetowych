package com.example.DTO;

import lombok.*;
import jakarta.validation.constraints.*;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class PoliceOfficerCreateUpdateDTO {

    @NotBlank(message = "Name cannot be blank")
    @Size(max = 50, message = "Name cannot exceed 50 characters")
    private String name;

    @Positive(message = "Badge number must be positive")
    private int badgeNumber;

    @Min(value = 0, message = "Years of service cannot be negative")
    @Max(value = 60, message = "Years of service cannot exceed 60")
    private int yearsOfService;

    @Min(value = 1, message = "Rank must be at least 1")
    private int rank;

    @NotNull(message = "Department ID cannot be null")
    private UUID departmentId;
}
