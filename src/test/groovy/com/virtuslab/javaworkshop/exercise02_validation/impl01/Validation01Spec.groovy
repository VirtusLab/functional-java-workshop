package com.virtuslab.javaworkshop.exercise02_validation.impl01


import spock.lang.Specification

class Validation01Spec extends Specification {
    /*@Unroll
    def "should return #expectedResult for #email"() {
        given:
        def toValidate = new ToValidate("anyname", email, "1", -123.00)
        Validation.Validator<ToValidate> validator = Validation01.getValidation()

        when:
        Validation.ValidationResult result = validator.validate(toValidate)

        then:
        result.isValid() == expectedResult

        where:
        email             | expectedResult
        "wrongEmail"      | false
        "email@email.com" | true
    }*/
}
