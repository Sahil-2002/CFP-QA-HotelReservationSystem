package org.example;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;


public class MainTest {

    @Test
    public void addhotel() {
        Main sc = new Main();
        sc.addhotel("Lakewood");
        sc.setregularrates("Lakewood", "Weekday", 110);
        Hotel lakewood = sc.hotels.get("Lakewood");
        assertEquals(110, lakewood.getRegularrates().get("Weekday"));


    }

    @Test
    public void findcheapesthotel() {
        Main sc = new Main();
        sc.addhotel("Lakewood");
        sc.addhotel("Bridgewood");
        sc.addhotel("Ridgewood");

        sc.setregularrates("Lakewood", "Weekday", 110);
        sc.setregularrates("Bridgewood", "Weekday", 160);
        sc.setregularrates("Ridgewood", "Weekday", 220);
        String startDate = "10sep2020";
        String endDate = "11sep2020";
        String hotel = sc.findCheapestHotel(startDate, endDate);
        assertEquals("Lakewood total rates 220", hotel);
    }

}