package com.example.OfficerService.entities;

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
@Table(name = "officers")
public class Officer {

    @Id
    @ToString.Exclude
    @Builder.Default
    @Column(name = "officer_id", nullable = false)
    private UUID id = UUID.randomUUID();

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "badge_number")
    private int badgeNumber;
    @Column(name = "years_of_service")
    private int yearsOfService;
    @Column(name = "rank")
    private int rank;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}
