package com.example.component;

import com.example.model.PoliceDepartment;
import com.example.model.PoliceOfficer;
import com.example.repository.PoliceDepartmentRepository;
import com.example.repository.PoliceOfficerRepository;
import com.example.service.PoliceDepartmentService;
import com.example.service.PoliceOfficerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

//user interaction
@Component
@Order(2)
public class CommandLineApp implements CommandLineRunner {

    private final PoliceOfficerService officerService;
    private final PoliceDepartmentService departmentService;

    private PoliceOfficerRepository repoOfficer;
    private PoliceDepartmentRepository repoDepartment;

    @Autowired
    public CommandLineApp(PoliceOfficerService officerService, PoliceDepartmentService departmentService, PoliceOfficerRepository repoOfficer, PoliceDepartmentRepository repoDepartment) {
        this.officerService = officerService;
        this.departmentService = departmentService;
        this.repoOfficer = repoOfficer;
        this.repoDepartment = repoDepartment;
    }


    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String command;

        System.out.println("Police Database:");
        do {
            System.out.println("Available commands: departments_list, officers_list, add_officer, delete_officer, add_department, delete_department, exit");
            command = scanner.nextLine();

            switch (command) {
                case "departments_list":
                    List<PoliceDepartment> listDepartment = repoDepartment.findAll();

                    for(int i=0; i<listDepartment.size(); i++) {
                        System.out.println(listDepartment.get(i));
                    }

                    break;
                case "officers_list":
                    List<PoliceOfficer> listOfficer = repoOfficer.findAll();

                    for(int i=0; i<listOfficer.size(); i++) {
                        System.out.println(listOfficer.get(i));
                    }
                    break;
                case "add_officer":
                    System.out.print("ENTER\n");
                    System.out.print("officer name: ");
                    String name = scanner.nextLine();
                    System.out.print("badge number: ");
                    int badgeNumber = Integer.parseInt(scanner.nextLine());
                    int serviceYears = 0;
                    do{
                        System.out.print("years of service: ");
                        serviceYears = Integer.parseInt(scanner.nextLine());

                    }while(serviceYears < 0 && serviceYears >= 60);
                    System.out.print("rank: ");
                    int rank = Integer.parseInt(scanner.nextLine());

                    PoliceOfficer officer = new PoliceOfficer();
                    officer.setName(name);
                    officer.setBadgeNumber(badgeNumber);
                    officer.setYearsOfService(serviceYears);
                    officer.setRank(rank);

                    repoOfficer.save(officer);
                    break;
                case "delete_officer":
                    System.out.print("ENTER\n");
                    System.out.print("Rank: ");
                    int officer_rank = Integer.parseInt(scanner.nextLine());

                    List<PoliceOfficer> officers = repoOfficer.findByRank(officer_rank);

                    for(int i=0; i<officers.size(); i++){
                        repoOfficer.delete(officers.get(i));
                    }
                    break;
                case "add_department":
                    System.out.print("ENTER\n");
                    System.out.print("department name: ");
                    String department_name = scanner.nextLine();
                    System.out.print("city: ");
                    String city = scanner.nextLine();
                    System.out.print("number of cases: ");
                    int cases = Integer.parseInt(scanner.nextLine());

                    PoliceDepartment department = new PoliceDepartment();
                    department.setName(department_name);
                    department.setCity(city);
                    department.setNumberOfCases(cases);
                    repoDepartment.save(department);
                    break;
                case "delete_department":
                    System.out.print("ENTER\n");
                    System.out.print("City: ");
                    String department_city = scanner.nextLine();

                    List<PoliceDepartment> departments = repoDepartment.findByCity(department_city);

                    repoDepartment.delete(departments.get(0));
                    break;
                case "exit":
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Unknown command");
            }
        } while (!command.equals("exit"));
    }
}
