package com.cloudbees.ecommerce.dto;


import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;

import com.cloudbees.ecommerce.annotation.ValidDiscountOrTax;
import com.cloudbees.ecommerce.util.Constants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComputeRequestDTO
{
	@NotBlank(message =Constants.ID_VALIDATION)
	private String id;
	
	@ValidDiscountOrTax(message = Constants.INVALID_TYPE_MESSAGE)
	@NotBlank(message = Constants.INVALID_TYPE_MESSAGE)
	private String discountOrTax;
	
	@DecimalMin(value = "1", inclusive = true, message = Constants.MIN_VALIDATION)
    @DecimalMax(value = "100", inclusive = true, message = Constants.MAX_VALIDATION_FOR_PERCENT)
	private double appliedPercent;
	
	
}