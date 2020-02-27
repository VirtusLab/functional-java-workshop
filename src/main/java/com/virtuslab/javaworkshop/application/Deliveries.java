package com.virtuslab.javaworkshop.application;

import java.util.List;

public interface Deliveries {
    
    List<Delivery> get(String productId);
    
}
