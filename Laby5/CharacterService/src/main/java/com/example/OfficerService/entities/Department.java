package com.example.OfficerService.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;
import java.util.ArrayList;
import java.util.List;

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
    @Column(name = "department_id")
    private UUID id = UUID.randomUUID();

    @ToString.Exclude
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Officer> officers = new ArrayList<>();

}
