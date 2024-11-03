package com.example.component;

import com.example.model.PoliceDepartment;
import com.example.model.PoliceOfficer;
import com.example.service.PoliceDepartmentService;
import com.example.service.PoliceOfficerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
@Order(1)
public class DataInitializer implements CommandLineRunner {

    private final PoliceOfficerService officerService;
    private final PoliceDepartmentService departmentService;

    @Autowired
    public DataInitializer(PoliceOfficerService officerService, PoliceDepartmentService departmentService) {
        this.officerService = officerService;
        this.departmentService = departmentService;
    }

    @Override
    public void run(String... args) {

        // DEPARTMENTS
        PoliceDepartment department1 = new PoliceDepartment();
        department1.setName("Central City PD");
        department1.setCity("New York");
        department1.setNumberOfCases(1);
        departmentService.saveDepartment(department1);

        PoliceDepartment department2 = new PoliceDepartment();
        department2.setName("Metro PD");
        department2.setCity("Las Vegas");
        department2.setNumberOfCases(2);
        departmentService.saveDepartment(department2);

        PoliceDepartment department3 = new PoliceDepartment();
        department3.setName("Harbor District PD");
        department3.setCity("Los Angeles");
        department3.setNumberOfCases(3);
        departmentService.saveDepartment(department3);

        PoliceDepartment department4 = new PoliceDepartment();
        department4.setName("Eastern Precinct PD");
        department4.setCity("Carlton");
        department4.setNumberOfCases(4);
        departmentService.saveDepartment(department4);

        PoliceDepartment department5 = new PoliceDepartment();
        department5.setName("Downtown Regional Police Bureau");
        department5.setCity("Houston");
        department5.setNumberOfCases(5);
        departmentService.saveDepartment(department5);

        List<PoliceDepartment> departments = Arrays.asList(department1, department2, department3, department4, department5);

        int number_of_officers = 30;
        Random random = new Random();

        for (int i=0; i<number_of_officers; i++){
            PoliceOfficer officer = new PoliceOfficer();
            officer.setName("OfficerName" + i);
            officer.setYearsOfService(random.nextInt(25));
            officer.setBadgeNumber(random.nextInt(1111, 10000));
            officer.setRank(random.nextInt(1, 10));
            PoliceDepartment randomDepartment = departments.get(random.nextInt(departments.size()));
            officer.setDepartment(randomDepartment);
            officerService.saveOfficer(officer);
        }
    }
}
