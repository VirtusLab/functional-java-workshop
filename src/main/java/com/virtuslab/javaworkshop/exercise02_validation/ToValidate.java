package com.virtuslab.javaworkshop.exercise02_validation;

import lombok.Value;

@Value
public class ToValidate {
    private String name;
    private String email;
    private String productId;
    private Double amount;
}