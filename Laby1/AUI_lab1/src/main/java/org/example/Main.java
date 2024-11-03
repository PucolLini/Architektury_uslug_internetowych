package org.example;

import java.io.*;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Main {
    private static ArrayList<PoliceDepartment> generateDepartments(){
        var allDepartments = new ArrayList<PoliceDepartment>();
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            PoliceDepartment department = null;
            department = PoliceDepartment.builder()
                    .name("Department#" + i + 1)
                    .city("City#" + random.nextInt(100,500))
                    .numberOfCases(random.nextInt(20, 60))
                    .policeOfficersList(new ArrayList<>())
                    .build();
            allDepartments.add(department);
        }

        for (int i = 0; i < 15; i++) {
            var departmentId = random.nextInt(5);
            var officer = PoliceOfficer.builder()
                    .name("Officer#" + i + 1)
                    .badgeNumber(random.nextInt(1000,9999))
                    .yearsOfService(random.nextInt(40))
                    .rank(random.nextInt(1,25))
                    .department(allDepartments.get(departmentId))
                    .build();
            allDepartments.get(departmentId).getPoliceOfficersList().add(officer);
        }
        return allDepartments;
    }
    private static void serializeDepartments(ArrayList<PoliceDepartment> departments, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(departments);
            System.out.println("Zserializowane departamenty znajduja sie w " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static ArrayList<PoliceDepartment> deserializeDepartments(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (ArrayList<PoliceDepartment>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        var policeDepartments = generateDepartments();

        //ZAD 2 - wypisanie wszystkich policjantów i departamentów
        System.out.println("ZADANIE 2");

        policeDepartments.forEach(
                department -> {
                    System.out.println(department);
                    department.getPoliceOfficersList().forEach(System.out::println);
                }
        );

        System.out.println("");

        //ZAD 3 - stream API do zebrania i wypisywania danych
        System.out.println("ZADANIE 3");

        // tworzymy strumien z listy departamentow, przeksztalcamy na strumien policjantow i zbieramy do seta
        Set<PoliceOfficer> allPoliceOfficers = policeDepartments.stream()
                .map(PoliceDepartment::getPoliceOfficersList) //mapowanie departamentow na ich listy policjantow
                .flatMap(Collection::stream) //takie spłaszczenie list do jednego streama policjantow
                .collect(Collectors.toSet()); //zebranie wszystkich policjantow do seta, dzieki temu beda tylko unikalne wartosci

        allPoliceOfficers.stream().forEach(System.out::println);
        System.out.println("");

        //ZAD 4
        System.out.println("ZADANIE 4");

        // Stream pipeline: filtracja -> sortowanie -> wypisywanie
        allPoliceOfficers.stream()
                .filter(officer -> officer.getYearsOfService() > 10)
                .sorted((o1, o2) -> Integer.compare(o1.getRank(), o2.getRank())) // ranga rosnąco
                .forEach(System.out::println);

        System.out.println("");

        //ZAD 5 - stream api, zmiana elementu kolekcji na dto, sortowanie (naturalny porzadek), zebranie w kolekcje, wyswietlanie
        System.out.println("ZADANIE 5");

        List<PoliceOfficerDTO> officerDTOList = allPoliceOfficers.stream()
                .map(officer -> new PoliceOfficerDTO(officer)) // Transformacja na DTO
                .sorted() // sortowanie naturalne według badgeNumber (bo implementuje Comparable)
                .collect(Collectors.toList());


        officerDTOList.stream().forEach(System.out::println);

        System.out.println("");

        //ZAD 6
        System.out.println("ZADANIE 6");

        String fileName = "departments.bin";

        serializeDepartments(policeDepartments, fileName);

        var deserializedDepartments = deserializeDepartments(fileName);

        System.out.println("\nZdeserializowane dane: \n");
        deserializedDepartments.forEach(
                department -> {
                    System.out.println("-" + department);
                    department.getPoliceOfficersList().forEach(System.out::println);
                }
        );

        System.out.println("");

        //ZAD 7
        System.out.println("ZADANIE 7");

        var pool = new ForkJoinPool(5);
        AtomicInteger counter = new AtomicInteger(1);
        System.out.println("Przetworzono: ");
        pool.submit(() -> allPoliceOfficers.stream().toList().parallelStream().forEach(officer -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            int currentCount = counter.getAndIncrement();
            System.out.println(currentCount + ".: " + officer);
        })).join();

        pool.shutdown();

        try {
            pool.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Wszyscy policjanci zostali przetworzeni");
    }
}