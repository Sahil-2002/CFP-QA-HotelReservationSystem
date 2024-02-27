package org.example;

import java.util.HashMap;
import java.util.Map;

class Hotel{
    private String name ;
    private Map<String, Integer> regularrates;
    public Hotel(String name){
        this.name= name;
this.regularrates= new HashMap<>();

    }
    public void setRegularrates(String dayytpe, int rate ){
        regularrates.put(dayytpe,rate);

    }
    public String getname(){
        return name;
    }
    public Map<String, Integer> getRegularrates(){
        return regularrates;

    }


}





public class Main {

    Map<String, Hotel> hotels;
    public Main(){
        this.hotels=new HashMap<>();

    }
    public void addhotel(String name){
        hotels.put(name,new Hotel(name));
    }
public void setregularrates(String hotelname , String daytype , int rate){
     Hotel  hotel = hotels.get(hotelname);
     if(hotel!=null){
         hotel.setRegularrates(daytype,rate);
     }
}
    public static void main(String[] args) {
        System.out.println("Welcome to Hotel Reservation Program ");

        Main sc = new Main();
        sc.addhotel("Lakewood");
        sc.addhotel("Bridgewood");
        sc.addhotel("Ridgewood");

        sc.setregularrates("Lakewood","Weekday",110);
        sc.setregularrates("Bridgewood","Weekday",160);
        sc.setregularrates("Ridgewood","Weekday", 220);


        Hotel lakewood = sc.hotels.get("Lakewood");

        if (lakewood != null) {
            System.out.println("Lakewood Rates for Regular Customers: " + lakewood.getRegularrates());
        }

    }
}