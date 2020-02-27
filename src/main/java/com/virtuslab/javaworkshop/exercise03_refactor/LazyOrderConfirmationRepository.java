package com.virtuslab.javaworkshop.exercise03_refactor;

import io.vavr.NotImplementedError;
import lombok.AllArgsConstructor;

import java.util.stream.Stream;

@AllArgsConstructor
public class LazyOrderConfirmationRepository {

    private OrderConfirmationRepository orderRepository;

    public Stream<OrderConfirmation> getOrders(int year, int size, int batchSize) {
        // TODO implement lazy stream which will page through orderRepository when needed
        throw new NotImplementedError("Not implemented yet!");
    }
}
