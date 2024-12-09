package com.example.component;

import com.example.model.PoliceDepartment;
import com.example.service.PoliceDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Order(1)
public class DataInitializer implements CommandLineRunner {

    private final PoliceDepartmentService departmentService;

    @Autowired
    public DataInitializer(PoliceDepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Override
    public void run(String... args) {

        // Tworzenie przykładowych obiektów PoliceDepartment
        PoliceDepartment department1 = new PoliceDepartment();
        department1.setName("Central City PD");
        department1.setCity("New York");
        department1.setNumberOfCases(10);
        departmentService.saveDepartment(department1);

        PoliceDepartment department2 = new PoliceDepartment();
        department2.setName("Metro PD");
        department2.setCity("Las Vegas");
        department2.setNumberOfCases(20);
        departmentService.saveDepartment(department2);

        PoliceDepartment department3 = new PoliceDepartment();
        department3.setName("Harbor District PD");
        department3.setCity("Los Angeles");
        department3.setNumberOfCases(30);
        departmentService.saveDepartment(department3);

        PoliceDepartment department4 = new PoliceDepartment();
        department4.setName("Eastern Precinct PD");
        department4.setCity("Carlton");
        department4.setNumberOfCases(40);
        departmentService.saveDepartment(department4);

        PoliceDepartment department5 = new PoliceDepartment();
        department5.setName("Downtown Regional Police Bureau");
        department5.setCity("Houston");
        department5.setNumberOfCases(50);
        departmentService.saveDepartment(department5);

        // Weryfikacja, że dane zostały poprawnie zapisane
        List<PoliceDepartment> departments = departmentService.getAllDepartments();
        System.out.println("Initialized Police Departments:");
        departments.forEach(department -> System.out.println(department.getName() + " - " + department.getCity()));
    }
}
