package com.virtuslab.javaworkshop.application;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.function.Predicate;

@Getter
@EqualsAndHashCode(callSuper = true)
public class ProductDiscount extends Discount<Basket.Selection> {
    
    private final String productId;

    private ProductDiscount(Integer discount, Predicate<Basket.Selection> isApplicable, String productId) {
        super(discount, isApplicable);
        this.productId = productId;
    }
    
    public static ProductDiscount of(Integer discount, String productId) {
        return new ProductDiscount(discount, s -> true, productId);
    }

    public static ProductDiscount of(Integer discount, Predicate<Basket.Selection> isApplicable, String productId) {
        return new ProductDiscount(discount, isApplicable, productId);
    }
    
    public static ProductDiscount noDiscount() {
        return new ProductDiscount(0, o -> false, null);
    }
    
}
