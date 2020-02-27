package com.virtuslab.javaworkshop.exercise03_refactor;

import com.virtuslab.javaworkshop.application.Basket;

public interface OrderRepository {
    
    void save(OrderConfirmation orderConfirmation, Basket basket);
    
    Basket get(OrderConfirmation orderConfirmation);
    
}
