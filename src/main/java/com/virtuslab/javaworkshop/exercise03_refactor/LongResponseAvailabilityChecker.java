package com.virtuslab.javaworkshop.exercise03_refactor;

import com.virtuslab.javaworkshop.application.Basket;

import java.time.Duration;

public class LongResponseAvailabilityChecker implements OrderService.AvailabilityChecker {
    
    @Override
    public Availability checkAvailability(Basket.Selection selection) {
        try {
            System.out.println("Checking: selection");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Availability.availableIn(Duration.ofDays(2));
    }
    
}
