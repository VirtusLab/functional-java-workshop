package com.virtuslab.javaworkshop.application;

import java.util.List;

public interface DiscountFinder {
    
    List<ProductDiscount> find(String userId);
    
}
