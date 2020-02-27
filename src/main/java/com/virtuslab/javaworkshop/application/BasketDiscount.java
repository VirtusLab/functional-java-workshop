package com.virtuslab.javaworkshop.application;

import java.util.function.Predicate;


public final class BasketDiscount extends Discount<Basket>{

    public BasketDiscount(Integer percent, Predicate<Basket> isApplicable) {
        super(percent, isApplicable);
    }
}
