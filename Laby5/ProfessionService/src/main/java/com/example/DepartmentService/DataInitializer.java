package com.example.DepartmentService;

import com.example.DepartmentService.entity.Department;
import com.example.DepartmentService.repositories.department.DepartmentRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DataInitializer {
    private final DepartmentRepository cr;
    private Random random = new Random(2137);

    @Autowired
    public DataInitializer(DepartmentRepository categoryRepository) {
        this.cr = categoryRepository;
    }

    @PostConstruct
    public void createAndLoadData() {
        if(!cr.findAll().isEmpty()) {
            return; // something already exists, no need to initialize
        }

        List<Department> departments = new ArrayList<>();
        List<String> departmentsNames = List.of("NCPD", "LAPD", "CaliPD");
        List<String> departmentsCities = List.of("New York", "Los Angeles", "California");
        for(int i = 0; i < departmentsNames.size(); i++) {
            Department department = Department.builder()
                    .id(UUID.nameUUIDFromBytes(departmentsNames.get(i).getBytes()))
                    .name(departmentsNames.get(i))
                    .city(departmentsCities.get(i))
                    .numberOfCases(random.nextInt(1, 12))
                    .build();
            departments.add(department);
        }
        cr.saveAll(departments);

    }
}
