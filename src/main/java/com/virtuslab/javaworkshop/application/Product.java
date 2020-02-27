package com.virtuslab.javaworkshop.application;

import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Value(staticConstructor = "product")
public class Product {
    
    public static final Product PLAYSTATION_5 = new Product("Playstation 4", 1100.0);
    public static final Product NINTENDO_SWITCH = new Product("Nintendo Switch", 1350.0);
    
    private String id = UUID.randomUUID().toString();
    private final String name;
    private final Double price;

    public static void main(String[] args) {
        var priceById = Stream.of(new Product("1", 100.0), new Product("2", 200.0))
                            .collect(Collectors.toMap(Product::getId, Product::getPrice));
    }
    
}

