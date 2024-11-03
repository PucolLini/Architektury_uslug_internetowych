package com.example.model;

import com.example.model.PoliceDepartment;
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
    int badgeNumber;
    @Column(name = "years_of_service")
    int yearsOfService;
    @Column(name = "rank")
    int rank;
    @ManyToOne
    @JoinColumn(name = "department_id")
    PoliceDepartment department;
}
