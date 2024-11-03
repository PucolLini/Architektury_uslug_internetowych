package org.example;

import lombok.*;

@Data //@Getter, @Setter, @ToString, @EqualsAndHashCode
@AllArgsConstructor
@Builder
public class PoliceOfficerDTO implements Comparable<PoliceOfficerDTO> {
    String name;
    int badgeNumber;
    String department;

    public PoliceOfficerDTO(PoliceOfficer officer){
        this.name = officer.getName();
        this.badgeNumber = officer.getBadgeNumber();
        this.department = officer.getDepartment().getName();
    }

    @Override
    public int compareTo(PoliceOfficerDTO o) {
        if (Integer.compare(badgeNumber, o.badgeNumber) != 0){
            if (this.name.compareTo(o.getName()) != 0){
                return this.department.compareTo(o.getDepartment());
            }
            return this.name.compareTo(o.getName());
        }
        return Integer.compare(badgeNumber, o.badgeNumber);
    }
}
