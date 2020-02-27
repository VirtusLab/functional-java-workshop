package com.virtuslab.javaworkshop.application;

import lombok.Value;

import java.util.Set;
import java.util.UUID;

@Value
public class Basket implements Price {
    
    private final String id;
    private final Set<Selection> selections;

    @Override
    public double calculatePrice() {
        return selections.stream()
                .mapToDouble(Selection::calculatePrice)
                .sum();
    }

    @Value(staticConstructor = "selection")
    public static class Selection implements Price {
        private final String selectionId = UUID.randomUUID().toString();
        private final String productId;
        private final Double price;
        private final Integer quantity;

        @Override
        public double calculatePrice() {
            return quantity * price;
        }
    }
    
}
