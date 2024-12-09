package com.example.component;

import com.example.model.PoliceOfficer;
import com.example.service.PoliceOfficerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Component
@Order(1)
public class DataInitializer implements CommandLineRunner {

    private final PoliceOfficerService officerService;
    private final RestTemplate restTemplate;

    private static final String CATEGORY_MANAGEMENT_URL = "http://localhost:8081/api/departments";

    @Autowired
    public DataInitializer(PoliceOfficerService officerService, RestTemplate restTemplate) {
        this.officerService = officerService;
        this.restTemplate = restTemplate;
    }

    @Override
    public void run(String... args) {

        // Pobieranie dostępnych departamentów z mikroserwisu categorymanagement
        List<UUID> departments = Arrays.asList(
                restTemplate.getForObject(CATEGORY_MANAGEMENT_URL + "/1", UUID.class),
                restTemplate.getForObject(CATEGORY_MANAGEMENT_URL + "/2", UUID.class),
                restTemplate.getForObject(CATEGORY_MANAGEMENT_URL + "/3", UUID.class),
                restTemplate.getForObject(CATEGORY_MANAGEMENT_URL + "/4", UUID.class),
                restTemplate.getForObject(CATEGORY_MANAGEMENT_URL + "/5", UUID.class)
        );

        int number_of_officers = 30;
        Random random = new Random();

        for (int i = 0; i < number_of_officers; i++) {
            PoliceOfficer officer = new PoliceOfficer();
            officer.setName("OfficerName" + i);
            officer.setYearsOfService(random.nextInt(25));
            officer.setBadgeNumber(random.nextInt(1111, 10000));
            officer.setRank(random.nextInt(1, 10));

            // Losowanie departamentu dla funkcjonariusza
            UUID randomDepartmentId = departments.get(random.nextInt(departments.size()));
            officer.setDepartmentId(randomDepartmentId); // Przypisanie tylko ID departamentu

            officerService.saveOfficer(officer);
        }
    }
}
