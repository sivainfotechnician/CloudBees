package com.cloudbees.ecommerce.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.cloudbees.ecommerce.util.Constants;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = { AlphaNumericValidator.class })
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface AlphaNumeric {

    String message() default Constants.INVALID_NAME_PATTERN;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}