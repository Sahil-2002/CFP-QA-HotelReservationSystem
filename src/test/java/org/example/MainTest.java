package org.example;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;


public class MainTest {

    @Test
    public void addhotel(){
        Main sc = new Main();
        sc.addhotel("Lakewood");
        sc.setregularrates("Lakewood","Weekday",110);
        Hotel lakewood = sc.hotels.get("Lakewood");
        assertEquals(110,lakewood.getRegularrates().get("Weekday"));


    }

}