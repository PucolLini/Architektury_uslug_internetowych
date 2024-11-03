package org.example;

import java.io.Serializable;
import java.util.List;
import lombok.*;

@Data //@Getter, @Setter, @ToString, @EqualsAndHashCode
@AllArgsConstructor
@Builder
public class PoliceDepartment implements Serializable {
    String name;
    String city;
    int numberOfCases;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    List<PoliceOfficer> policeOfficersList;
}
