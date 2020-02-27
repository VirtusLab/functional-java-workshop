package com.virtuslab.javaworkshop.exercise03_refactor;

import com.virtuslab.javaworkshop.application.Basket;

import java.util.HashMap;
import java.util.Map;

public class TestOrderRepositoryImpl implements OrderRepository {

    private Map<OrderConfirmation, Basket> orders = new HashMap<>();

    @Override
    public void save(OrderConfirmation orderConfirmation, Basket basket) {
        this.orders.put(orderConfirmation, basket);
    }

    @Override
    public Basket get(OrderConfirmation orderConfirmation) {
        return this.orders.get(orderConfirmation);
    }
}
