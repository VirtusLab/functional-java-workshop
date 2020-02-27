package com.virtuslab.javaworkshop.exercise03_refactor;

import lombok.Value;

import java.time.LocalDateTime;

@Value(staticConstructor = "of")
class OrderConfirmation {
    private final String orderId;
    private final LocalDateTime reservationDate;
    private final Double totalAmount;
}
