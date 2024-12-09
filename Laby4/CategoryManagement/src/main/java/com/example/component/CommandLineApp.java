package com.example.component;

import com.example.model.PoliceDepartment;
import com.example.repository.PoliceDepartmentRepository;
import com.example.service.PoliceDepartmentService;
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

    private final PoliceDepartmentService departmentService;

    private PoliceDepartmentRepository repoDepartment;

    @Autowired
    public CommandLineApp(PoliceDepartmentService departmentService, PoliceDepartmentRepository repoDepartment) {
        this.departmentService = departmentService;
        this.repoDepartment = repoDepartment;
    }


    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String command;

        System.out.println("Police Database:");
        do {
            System.out.println("Available commands: departments_list, add_department, delete_department, exit");
            command = scanner.nextLine();

            switch (command) {
                case "departments_list":
                    List<PoliceDepartment> listDepartment = repoDepartment.findAll();

                    for(int i=0; i<listDepartment.size(); i++) {
                        System.out.println(listDepartment.get(i));
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
