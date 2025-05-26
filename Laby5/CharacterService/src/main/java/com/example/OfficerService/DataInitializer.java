package com.example.OfficerService;

import com.example.OfficerService.entities.Officer;
import com.example.OfficerService.entities.Department;
import com.example.OfficerService.services.officer.OfficerService;
import com.example.OfficerService.services.department.DepartmentService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Component
public class DataInitializer {
    private final OfficerService cs;
    private final DepartmentService ps;
    private Random random = new Random(2137);

    @Autowired
    public DataInitializer(OfficerService officerService, DepartmentService departmentService) {
        this.cs = officerService;
        this.ps = departmentService;
    }

    @PostConstruct
    public void createAndLoadData() {
        if(!cs.findAll().isEmpty() || !ps.findAll().isEmpty()) {
            return;
        }

        List<Department> departments = new ArrayList<>();
        List<String> departmentNames = List.of("NCPD", "LAPD", "CaliPD");
        for(int i = 0; i < departmentNames.size(); i++) {
            Department department = Department.builder()
                    .id(UUID.nameUUIDFromBytes(departmentNames.get(i).getBytes()))
                    .build();
            departments.add(department);
            ps.create(department);
        }

        List<String> officerNames = List.of("Jan", "Adam", "Karolina", "Patrycja");
        for(int i = 0; i < officerNames.size(); i++) {
            Officer officer = Officer.builder()
                    .id(UUID.nameUUIDFromBytes(officerNames.get(i).getBytes()))
                    .name(officerNames.get(i))
                    .badgeNumber(random.nextInt(0, 100))
                    .yearsOfService(random.nextInt(0, 40))
                    .rank(random.nextInt(1, 10))
                    .department(departments.get(i % departmentNames.size()))
                    .build();
            cs.create(officer);
        }
    }
}
