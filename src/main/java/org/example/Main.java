package org.example;

import java.util.HashMap;
import java.util.Map;

class Hotel {
    private String name;
    private Map<String, Integer> regularrates;

    public Hotel(String name) {
        this.name = name;
        this.regularrates = new HashMap<>();

    }

    public void setRegularrates(String dayytpe, int rate) {
        regularrates.put(dayytpe, rate);

    }

    public String getname() {
        return name;
    }

    public Map<String, Integer> getRegularrates() {
        return regularrates;

    }

    public int getTotalRate(String dayType, int numDays) {
        return regularrates.getOrDefault(dayType, 0) * numDays;
    }
}

public class Main {

    Map<String, Hotel> hotels;

    public Main() {
        this.hotels = new HashMap<>();

    }

    public void addhotel(String name) {
        hotels.put(name, new Hotel(name));
    }

    public void setregularrates(String hotelname, String daytype, int rate) {
        Hotel hotel = hotels.get(hotelname);
        if (hotel != null) {
            hotel.setRegularrates(daytype, rate);
        } else {
            System.out.println("hotel " + hotelname + " not found ");
        }
    }

    public String findCheapestHotel(String startDate, String endDate) {
        int minTotalRate = Integer.MAX_VALUE;

        String cheapestHotel = "";

        for (Hotel hotel : hotels.values()) {
            int totalRate = hotel.getTotalRate("Weekday", 2);
            if (totalRate < minTotalRate) {
                minTotalRate = totalRate;
                cheapestHotel = hotel.getname();
            }
        }

        return cheapestHotel + " total rates " + minTotalRate;
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Hotel Reservation Program ");

        Main sc = new Main();
        sc.addhotel("Lakewood");
        sc.addhotel("Bridgewood");
        sc.addhotel("Ridgewood");

        sc.setregularrates("Lakewood", "Weekday", 110);
        sc.setregularrates("Bridgewood", "Weekday", 150);
        sc.setregularrates("Ridgewood", "Weekday", 220);

        sc.setregularrates("Lakewood", "Weekend", 90);
        sc.setregularrates("Bridgewood", "Weekend", 50);
        sc.setregularrates("Ridgewood", "Weekend", 150);


        Hotel lakewood = sc.hotels.get("Lakewood");

        if (lakewood != null) {
            System.out.println("Lakewood Rates for Regular Customers: " + lakewood.getRegularrates());
        }
        String startDate = "10sep2020";
        String endDate = "11sep2020";
        String hotel = sc.findCheapestHotel(startDate, endDate);
        System.out.println(hotel);


    }
}