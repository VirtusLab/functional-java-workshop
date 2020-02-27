package com.virtuslab.javaworkshop.exercise02_validation.impl04

import spock.lang.Specification

class Validation04Spec extends Specification {

    /*def static validName = "validName"
    def static validEmail = "email@email.com"
    def static validAmount = 100.00
    def static validProductId = "12345678901234567890"

    @Unroll
    def "should return #expectedResult for #email, #amount"(String email, Double amount, String productId, boolean expectedResult) {
        given:
        def toValidate = new com.virtuslab.javaworkshop.exercise02_validation.ToValidate(validName, email, productId, amount)
        Validator<com.virtuslab.javaworkshop.exercise02_validation.ToValidate> validator = Validation04.getValidation()

        when:
        Validation04.ValidationResult result = validator.validate(toValidate)

        then:
        result.isValid() == expectedResult

        where:
        email        | amount      | productId      || expectedResult
        "wrongEmail" | -100.00     | "invalid"      || false
        validEmail   | -100.00     | "invalid"      || false
        validEmail   | validAmount | "invalid"      || false
        validEmail   | validAmount | validProductId || true
    }

    def "should return message for invalid field"() {
        given:
        def toValidate = new com.virtuslab.javaworkshop.exercise02_validation.ToValidate(validName, validEmail, validProductId, -100.00)
        Validator<com.virtuslab.javaworkshop.exercise02_validation.ToValidate> validator = Validation04.getValidation()
        
        when:
        Validation04.ValidationResult result = validator.validate(toValidate)
        
        then:
        result.getInvalidFields().get(0).toString() == "Field: amount has invalid value: -100.0. Valid amount is required in field: amount"
        result.getInvalidFields().get(0).getName() == "amount"
        result.getInvalidFields().get(0).getValue() == -100.00
        
    }*/
}
