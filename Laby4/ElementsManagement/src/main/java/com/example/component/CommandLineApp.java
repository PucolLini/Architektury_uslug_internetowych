package com.example.component;

import com.example.model.PoliceOfficer;
import com.example.repository.PoliceOfficerRepository;
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

    private PoliceOfficerRepository repoOfficer;

    @Autowired
    public CommandLineApp(PoliceOfficerService officerService, PoliceOfficerRepository repoOfficer) {
        this.officerService = officerService;
        this.repoOfficer = repoOfficer;
    }


    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String command;

        System.out.println("Police Database:");
        do {
            System.out.println("Available commands: officers_list, add_officer, delete_officer, exit");
            command = scanner.nextLine();

            switch (command) {
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
                case "exit":
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Unknown command");
            }
        } while (!command.equals("exit"));
    }
}
