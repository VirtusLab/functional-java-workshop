package com.virtuslab.javaworkshop.exercise02_validation.impl01

import com.virtuslab.javaworkshop.exercise06_validation.ToValidate
import com.virtuslab.javaworkshop.exercise06_validation.Validation
import spock.lang.Specification
import spock.lang.Unroll

class Validation01Spec extends Specification {
    @Unroll
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
    }
}
