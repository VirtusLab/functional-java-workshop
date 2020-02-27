package com.virtuslab.javaworkshop.application;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.function.Predicate;

@ToString
@Getter
@EqualsAndHashCode
public class Discount<T> {
    private final Integer discount;
    private final Predicate<T> isApplicable;
    
    public Discount(Integer discount, Predicate<T> isApplicable) {
        this.discount = discount;
        this.isApplicable = isApplicable;
    }

    public Discount(Integer discount) {
        this.discount = discount;
        this.isApplicable = t -> true;
    }
    
    public double priceAfterDiscount(double price, T object) {
        return isApplicable != null && isApplicable.test(object) ? price - (price * discount / 100) : price;  
    }
}
