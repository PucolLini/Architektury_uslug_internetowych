package org.example;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        var partners = generateData();
        System.out.println("TASK 1,2");
        partners.forEach(
                partner -> {
                    System.out.println(partner);
                    partner.getOffers().forEach(System.out::println);
                }
        );
        System.out.println("TASK 3");
        Set<Offer> allOffers = partners.stream()
                .map(Partner::getOffers)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());

        allOffers.forEach(System.out::println);

        System.out.println("TASK 4");
        allOffers.stream()
                .filter(Offer -> Offer.getPartner().getSinceYear()<2018)
                .sorted(Comparator.comparing(Offer::getValidTo))
                .forEach(System.out::println);

        System.out.println("TASK 5");
        List<OfferDto> dtoList = allOffers.stream()
                .map(OfferDto::new)
                .sorted().collect(Collectors.toList());

        dtoList.forEach(System.out::println);

        System.out.println("TASK 6");
        serializeData(partners,"data.bin");
        var deserialized = deserializeData("data.bin");
        deserialized.forEach(
                partner -> {
                    System.out.println(partner);
                    partner.getOffers().forEach(System.out::println);
                }
        );

        System.out.println("TASK 7");
        ForkJoinPool fjp = new ForkJoinPool(5);
        Runnable job = () -> partners.parallelStream().forEach(partner -> {
            System.out.println(partner);
            partner.getOffers().forEach(Offer -> {
                try {
                    System.out.println(Offer);
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        });
        fjp.submit(job).join();
        fjp.shutdown();
    }

    private static ArrayList<Partner> generateData(){
        var Partners = new ArrayList<Partner>();
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            Partner partner = null;
            try {
                partner = Partner.builder()
                        .Name("Partner" + i)
                        .Website(new URL("https://partner"+i+".com"))
                        .Offers(new ArrayList<>())
                        .SinceYear(random.nextInt(2024-2000)+2000)
                        .build();
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
            Partners.add(partner);
        }

        for (int i = 0; i < 10; i++) {
            var PartnerId = random.nextInt(5);
            var fromDate = new Date(random.nextInt(2024-2000)+110,random.nextInt(12)+1,random.nextInt(30)+1);
            var offer = Offer.builder()
                    .Title("Offer" + i)
                    .Description("Lorem ipsum...")
                    .ValidFrom(fromDate)
                    .ValidTo(new Date(fromDate.getYear(),fromDate.getMonth()+2,fromDate.getDay()))
                    .Partner(Partners.get(PartnerId))
                    .build();
            Partners.get(PartnerId).getOffers().add(offer);
        }
        return Partners;
    }

    private static void serializeData(ArrayList<Partner> partners, String filename){
        try(FileOutputStream fos = new FileOutputStream(filename)) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(partners);
            oos.close();
        } catch (IOException e) {
            System.err.println("Failed to open output stream: "+e);
        }
    }

    private static ArrayList<Partner> deserializeData(String filename){
        ArrayList<Partner> partners = new ArrayList<>();
        try(FileInputStream fis = new FileInputStream(filename)){
            ObjectInputStream ois = new ObjectInputStream(fis);
            partners = (ArrayList<Partner>) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Failed to open input stream: "+e);
        }
        return partners;
    }
}