package org.example;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

class Hotel {
    private String name;
    private int rating;
    private Map<String, Integer> regularrates;

    public Hotel(String name) {
        this.name = name;
        this.regularrates = new HashMap<>();

    }

    public void setRegularrates(String dayytpe, int rate) {
        regularrates.put(dayytpe, rate);

    }
    public void setrating(int rating){
        this.rating=rating;
    }
    public int getRating(){
        return rating;
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
    public void setRatings(String hotelname , int rating){
        Hotel hotel = hotels.get(hotelname);
        if(hotel!= null){
            hotel.setrating(rating);
        }
        else {
            System.out.println("Hotel"+hotelname+" not found !");
        }
    }
    public int getRating(String hotelname){
        Hotel hotel = hotels.get(hotelname);
        if(hotel!= null){
           return hotel.getRating();
        }
        else {
            System.out.println("Hotel "+hotelname+" not found ");
        }
        return 0;

    }

    public String findCheapestHotel(String startDate, String endDate) {

        LocalDate date1 = LocalDate.parse(startDate);
        LocalDate date2 = LocalDate.parse(endDate);
        int daysDifference = (int) ChronoUnit.DAYS.between(date1, date2);

        int minTotalRate = Integer.MAX_VALUE;

        String cheapestHotel = "";

        for (Hotel hotel : hotels.values()) {
            int totalRate =0;
            for(LocalDate date = date1; !date.isAfter(date2);date = date.plusDays(1)){
                if(date.getDayOfWeek()== DayOfWeek.SATURDAY || date.getDayOfWeek()==DayOfWeek.SUNDAY){
                    totalRate = totalRate+hotel.getTotalRate("Weekend",daysDifference);
                }
                else {
                    totalRate = totalRate+ hotel.getTotalRate("Weekday",daysDifference);
                }
            }
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

       sc.setRatings("Lakewood",3);
       sc.setRatings("Bridgewood",4);
       sc.setRatings("Ridgewood",5);

        System.out.println("Rating for hotel Bridgewood is "+sc.getRating("Bridgewood"));


        String startDate = "2020-09-11";
        String endDate = "2020-09-12";
        String hotel = sc.findCheapestHotel(startDate, endDate);
        System.out.println(hotel);


    }
}