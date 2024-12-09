package com.example.model;

import lombok.*;
import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.*;

@Data //@Getter, @Setter, @ToString, @EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name="police_officers")
public class PoliceOfficer implements Serializable {

    @Id
    @Builder.Default
    @Column(name = "id")
    private UUID id = UUID.randomUUID(); // Generate UUID on creation

    @Column(name = "name")
    private String name;

    @Column(name = "badge_number")
    private int badgeNumber;

    @Column(name = "years_of_service")
    private int yearsOfService;

    @Column(name = "rank")
    private int rank;

    // Tylko ID departamentu, bez pełnej definicji departamentu
    @Column(name = "department_id")
    private UUID departmentId;
}
