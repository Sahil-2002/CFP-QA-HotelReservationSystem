package org.example;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

class Hotel {
    private String name;
    private int rating;
    private Map<String, Integer> regularrates;
    private Map<String, Integer> rewardrates;
    public Hotel(String name) {
        this.name = name;
        this.regularrates = new HashMap<>();
        this.rewardrates= new HashMap<>();

    }

    public void setRegularrates(String dayytpe, int rate) {
        regularrates.put(dayytpe, rate);

    }

    public void setrewardrates(String daytype, int rate) {
        rewardrates.put(daytype, rate);
    }

    public void setrating(int rating) {
        this.rating = rating;
    }

    public int getRating() {
        return rating;
    }

    public String getname() {
        return name;
    }

    public Map<String, Integer> getRegularrates() {
        return regularrates;

    }

    public Map<String, Integer> getrewardrates() {
        return rewardrates;
    }


    public int getTotalRate(String dayType, int numDays, boolean isReward) {
        Map<String, Integer> rates = isReward? rewardrates:regularrates;
        return rates.getOrDefault(dayType, 0) * numDays;
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
            throw new IllegalArgumentException("hotel " + hotelname + " not found ");
        }
    }

    public void setRewardrates(String hotelname, String daytype, int rate) {
        Hotel hotel = hotels.get(hotelname);
        if (hotel != null) {
            hotel.setrewardrates(daytype, rate);
        } else {
            throw  new IllegalArgumentException("hotel " + hotelname + " not found ");
        }
    }

    public void setRatings(String hotelname, int rating) {
        Hotel hotel = hotels.get(hotelname);
        if (hotel != null) {
            hotel.setrating(rating);
        } else {
            throw  new IllegalArgumentException("Hotel" + hotelname + " not found !");
        }
    }

    public int getRating(String hotelname) {
        Hotel hotel = hotels.get(hotelname);
        if (hotel != null) {
            return hotel.getRating();
        } else {
           throw new IllegalArgumentException("Hotel " + hotelname + " not found ");
        }

    }

    public String findCheapestHotel(String startDate, String endDate, boolean isReward) {

    LocalDate date1;
    LocalDate date2 ;
try{
    date1 = LocalDate.parse(startDate);
    date2 = LocalDate.parse(endDate);
}catch (DateTimeParseException e){
    throw new IllegalArgumentException("Invalid date format. Please use yyyy-mm-dd");
}
        if (!isReward && hotels.isEmpty()) {
            throw new IllegalStateException("No hotels available for regular customers");
        } else if (isReward && hotels.isEmpty()) {
            throw new IllegalStateException("No hotels available for reward customers");
        }

        int minTotalRate = Integer.MAX_VALUE;

        String cheapestHotel = "";
        for (Hotel hotel : hotels.values()) {

            int totalRate = 0;
            for (LocalDate date = date1; !date.isAfter(date2); date = date.plusDays(1)) {
                DayOfWeek dayOfWeek = date.getDayOfWeek();
                if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
                    if (isReward) {
                        totalRate += hotel.getTotalRate("Weekend", 1, true);

                    }
                    else{
                        totalRate += hotel.getTotalRate("Weekend", 1, false);

                    }
                }else {
                    if(isReward){
                        totalRate += hotel.getTotalRate("Weekday", 1, true);

                    }
                    else {
                        totalRate += hotel.getTotalRate("Weekday", 1, false);

                    }


                }
            }


            if (totalRate < minTotalRate) {
                minTotalRate = totalRate;
                cheapestHotel = hotel.getname();

            }
        }

        return cheapestHotel + " total rates " + minTotalRate;
    }

    public String bestratinghotel(String startDate, String endDate, boolean isReward) {
        LocalDate date1, date2;
        try {
            date1 = LocalDate.parse(startDate);
            date2 = LocalDate.parse(endDate);
        } catch(DateTimeParseException e){
            throw new IllegalArgumentException("Invalid date format. Please use yyyy-mm-dd");
        }

        if (hotels.isEmpty()) {
            throw new IllegalStateException("No hotels available.");
        }

        int maxRating = 0;
        String bestHotel = "";
        int bestTotalRate = Integer.MAX_VALUE;

        for (Hotel hotel : hotels.values()) {
            int totalRate = 0;

            for (LocalDate date = date1; !date.isAfter(date2); date = date.plusDays(1)) {
                DayOfWeek dayOfWeek = date.getDayOfWeek();
                if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
                    if (isReward) {
                        totalRate += hotel.getTotalRate("Weekend", 1, true);

                    }
                    else{
                        totalRate += hotel.getTotalRate("Weekend", 1, false);

                    }
                }else {
                    if(isReward){
                        totalRate += hotel.getTotalRate("Weekday", 1, true);

                    }
                    else {
                        totalRate += hotel.getTotalRate("Weekday", 1, false);

                    }


                }
            }

            if (hotel.getRating() > maxRating || (hotel.getRating() == maxRating && totalRate < bestTotalRate)) {
                maxRating = hotel.getRating();
                bestHotel = hotel.getname();
                bestTotalRate = totalRate;
            }
        }
        return "The best rated hotel is " + bestHotel + " with rating " + maxRating + " and with total rate " + bestTotalRate;
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

        sc.setRewardrates("Lakewood", "Weekday", 80);
        sc.setRewardrates("Bridgewood", "Weekday", 110);
        sc.setRewardrates("Ridgewood", "Weekday", 100);

        sc.setRewardrates("Lakewood", "Weekend", 80);
        sc.setRewardrates("Bridgewood", "Weekend", 50);
        sc.setRewardrates("Ridgewood", "Weekend", 40);


        sc.setRatings("Lakewood", 3);
        sc.setRatings("Bridgewood", 4);
        sc.setRatings("Ridgewood", 5);

        Hotel lakewood = sc.hotels.get("Lakewood");
        if (lakewood != null) {
            System.out.println("Reward rate for Lakewood is " + lakewood.getrewardrates()+lakewood.getRegularrates());
        }


        String startDate = "2020-09-11";
        String endDate = "2020-09-12";

        System.out.println(sc.bestratinghotel(startDate, endDate,true));  //checking for reward customer
        System.out.println(sc.bestratinghotel(startDate, endDate,false)); // checking for regular customer

        System.out.println(sc.findCheapestHotel(startDate, endDate,true));  //checking for reward customer
        System.out.println(sc.findCheapestHotel(startDate, endDate,false)); //checking for regular customer


    }
}