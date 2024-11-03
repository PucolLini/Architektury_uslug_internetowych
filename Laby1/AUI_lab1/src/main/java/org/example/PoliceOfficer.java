package org.example;

import lombok.*;
import java.io.Serializable;

@Data //@Getter, @Setter, @ToString, @EqualsAndHashCode
@AllArgsConstructor
@Builder
public class PoliceOfficer implements Serializable {
    String name;
    int badgeNumber;
    int yearsOfService;
    int rank;
    PoliceDepartment department;
}
