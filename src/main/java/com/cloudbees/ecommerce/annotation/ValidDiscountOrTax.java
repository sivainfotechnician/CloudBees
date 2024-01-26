package com.cloudbees.ecommerce.annotation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = { DiscountOrTaxValidator.class })
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDiscountOrTax {
    String message() ;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}