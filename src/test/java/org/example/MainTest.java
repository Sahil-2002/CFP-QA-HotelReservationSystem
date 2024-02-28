package org.example;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;


public class MainTest {

    @Test//1
    public void addhotel() {
        Main sc = new Main();
        sc.addhotel("Lakewood");
        sc.setregularrates("Lakewood", "Weekday", 110);
        Hotel lakewood = sc.hotels.get("Lakewood");
        assertEquals(110, lakewood.getRegularrates().get("Weekday"));


    }

    @Test//2
    public void findcheapesthotel() {
        Main sc = new Main();
        sc.addhotel("Lakewood");
        sc.addhotel("Bridgewood");
        sc.addhotel("Ridgewood");

        sc.setregularrates("Lakewood", "Weekday", 110);
        sc.setregularrates("Bridgewood", "Weekday", 160);
        sc.setregularrates("Ridgewood", "Weekday", 220);
        String startDate = "2020-09-10";
        String endDate = "2020-09-11";
        String hotel = sc.findCheapestHotel(startDate, endDate);
        assertEquals("Lakewood total rates 220", hotel);
    }


    @Test//3
    public void addrates() {
        Main sc = new Main();
        sc.addhotel("Lakewood");
        sc.setregularrates("Lakewood", "Weekday", 110);
        sc.setregularrates("Lakewood", "Weekend", 90);
        Hotel lakewood = sc.hotels.get("Lakewood");
        if (lakewood != null) {
            int rate1 = lakewood.getRegularrates().get("Weekday");
            int rate2 = lakewood.getRegularrates().get("Weekend");
            assertEquals(110, rate1);
            assertEquals(90, rate2);
        }

    }

    @Test//4
    public void findcheapest() {
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

        String startDate = "2020-09-11";
        String endDate = "2020-09-12";
        String hotel = sc.findCheapestHotel(startDate, endDate);
        assertEquals("Bridgewood total rates 200", hotel);
    }

    @Test //5
    public void getrating() {
        Main sc = new Main();
        sc.addhotel("Lakewood");
        sc.addhotel("Bridgewood");
        sc.addhotel("Ridgewood");

        sc.setRatings("Lakewood", 3);
        sc.setRatings("Bridgewood", 4);
        sc.setRatings("Ridgewood", 5);

        assertEquals(3, sc.getRating("Lakewood"));
        assertEquals(4, sc.getRating("Bridgewood"));
        assertEquals(5, sc.getRating("Ridgewood"));

    }

    @Test //6
    public void findcheapestbutbestrated() {
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

        sc.setRatings("Lakewood", 3);
        sc.setRatings("Bridgewood", 4);
        sc.setRatings("Ridgewood", 5);

        String startDate = "2020-09-11";
        String endDate = "2020-09-12";

        String hotel = sc.findCheapestHotel(startDate, endDate);
        assertEquals("Bridgewood total rates 200", hotel);
        assertEquals(4, sc.getRating("Bridgewood"));

    }

    @Test//7

    public void findbestrated() {
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

        sc.setRatings("Lakewood", 3);
        sc.setRatings("Bridgewood", 4);
        sc.setRatings("Ridgewood", 5);

        String startDate = "2020-09-11";
        String endDate = "2020-09-12";
        sc.bestratinghotel(startDate, endDate);
        assertEquals("The best rated hotel is Ridgewood with rating 5 and with total rate 370", sc.bestratinghotel(startDate, endDate));
    }

    @Test //8
    public void rewardrates() {
        Main sc = new Main();
        sc.addhotel("Lakewood");
        sc.addhotel("Bridgewood");
        sc.addhotel("Ridgewood");


        sc.setRewardrates("Lakewood", "Weekday", 80);
        sc.setRewardrates("Bridgewood", "Weekday", 110);
        sc.setRewardrates("Ridgewood", "Weekday", 100);

        sc.setRewardrates("Lakewood", "Weekend", 80);
        sc.setRewardrates("Bridgewood", "Weekend", 50);
        sc.setRewardrates("Ridgewood", "Weekend", 40);

        Hotel lakewood = sc.hotels.get("Lakewood");
        assertEquals(80, lakewood.getrewardrates().get("Weekday"));
        assertEquals(80, lakewood.getrewardrates().get("Weekend"));


    }

}