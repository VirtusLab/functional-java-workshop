package com.virtuslab.javaworkshop.application;

import lombok.Value;

import java.time.LocalDate;

@Value
public class Delivery {
    
    private LocalDate date;
    private Integer quantity;
    
}
