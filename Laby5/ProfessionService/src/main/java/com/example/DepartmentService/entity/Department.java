package com.example.DepartmentService.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Builder
@Table(name = "departments")
public class Department {

    @Id
    @ToString.Exclude
    @Builder.Default
    @Column(name = "department_id", nullable = false)
    private UUID id = UUID.randomUUID();

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "number_of_cases")
    private int numberOfCases;

}
