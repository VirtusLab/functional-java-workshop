package com.virtuslab.javaworkshop.exercise02_validation.impl02

import com.virtuslab.javaworkshop.exercise06_validation.ToValidate
import spock.lang.Specification
import spock.lang.Unroll

class Validation02Spec extends Specification {
    @Unroll
    def "should return #expectedResult for #email, #amount"(String email, Double amount, String productId, boolean expectedResult) {
        given:
        def toValidate = new ToValidate("anyname", email, productId, amount)
        Validator<ToValidate> validator = Validation02.getValidation()

        when:
        Validation02.ValidationResult result = validator.validate(toValidate)

        then:
        result.isValid() == expectedResult

        where:
        email             | amount  | productId              || expectedResult
        "wrongEmail"      | -100.00 | "invalid"              || false
        "email@email.com" | -100.00 | "invalid"              || false
        "email@email.com" | 1000.00 | "invalid"              || false
        "email@email.com" | 1000.00 | "12345678901234567890" || true
    }
}
