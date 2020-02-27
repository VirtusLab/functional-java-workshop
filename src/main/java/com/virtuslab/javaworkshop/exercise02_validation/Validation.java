package com.virtuslab.javaworkshop.exercise02_validation;

import lombok.Value;

/*
  TODO write a validation framework which will be able to:
  1. perform simple, static validation of specified fields:
    - check if value is not null when is required
    - check fixed / min / max length
    - check if amount field is a correct amount (>0)
    - check if field is correct email
    
    ToValidate:
    - name - string with minimum 3 chars
    - email - correct email address (implementation provided in EmailValidator
    - amount - non-negative double value
    - productId - string with fixed length of 20 chars
    
  2. Validation result should contain human friendly message with a name of validated field (or custom name)
  
  3. Add a validator which will pass if value is one of few fixed values
  
  4. Custom message can be added to message in case of error.
  
  5. Add dependsOn method which means that validation will be triggered only if other field is valid
  
  Final result should be something like this:         
  
  var validation = Validation.<ToValidate>validation()
            .field(ToValidate::getEmail, "email", email())
            .field(ToValidate::getAmount, "amount", amount())
            .field(ToValidate::getAmount, "amount", required())
            .field(ToValidate::getName, "name", minLength(3))
            .field(ToValidate::getProductId, "productId", fixedLength(20));
          
  and used like:
  
  validation.validate(toValidate)
  
  Response should contain list of errors in form:
  name, value, message
  
  like: 
  Field: amount has invalid value: -100.0. Valid amount is required in field: amount
 */

public class Validation {
    
    static class EmailValidator {
        public static boolean isValid(String email) {
            String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
            return email.matches(regex);
        }
    }

    @Value(staticConstructor = "of")
    static class ValidationResult {
        boolean valid;
    }

    @FunctionalInterface
    interface Validator<T> {
        ValidationResult validate(T object);
    }

}


