package com.cloudbees.ecommerce.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DiscountOrTaxValidator implements ConstraintValidator<ValidDiscountOrTax, String> {
    @Override
    public void initialize(ValidDiscountOrTax constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && (value.equalsIgnoreCase("Discount") || value.equalsIgnoreCase("Tax"));
    }
}