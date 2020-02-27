package com.virtuslab.javaworkshop.exercise03_refactor;

import lombok.Value;

@Value(staticConstructor = "of")
class PaymentConfirmation {
    private final String paymentId;
}
