package com.example.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "police_departments")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PoliceDepartment implements Serializable {

    @Id
    @Builder.Default
    @Column(name = "department_id")
    private UUID id = UUID.randomUUID();

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "number_of_cases")
    private int numberOfCases;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<PoliceOfficer> policeOfficersList = new ArrayList<>();
}
