package com.virtuslab.javaworkshop.common;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;

public class TestingClock extends Clock {

    private Clock clock = fixed(Instant.now(), ZoneId.systemDefault());

    private Duration duration = Duration.ZERO;

    @Override
    public ZoneId getZone() {
        return ZoneId.systemDefault();
    }

    @Override
    public Clock withZone(ZoneId zone) {
        return clock.withZone(zone);
    }

    public void plus(Duration duration) {
        this.duration = this.duration.plus(duration);
    }

    @Override
    public Instant instant() {
        return clock.instant().plus(duration);
    }
    
}
