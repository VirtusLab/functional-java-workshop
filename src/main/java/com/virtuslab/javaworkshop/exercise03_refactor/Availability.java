package com.virtuslab.javaworkshop.exercise03_refactor;

import lombok.Value;

import java.time.Duration;

@Value
class Availability {
    private final Duration availableIn;
    private final boolean available;

    public static Availability availableIn(Duration duration) {
        return new Availability(duration, true);
    }

    public static Availability notAvailable() {
        return new Availability(null, false);
    }
    
    public boolean availableInDays(int days) {
        return isAvailable() && availableIn.compareTo(Duration.ofDays(days)) < 0;
    }
    
}

