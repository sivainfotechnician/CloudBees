package com.cloudbees.ecommerce.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.cloudbees.ecommerce.util.Constants;

public class AlphaNumericValidator implements ConstraintValidator<AlphaNumeric, String> {

    @Override
    public void initialize(AlphaNumeric constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && value.matches(Constants.REGEX_PATTERN);
    }
}