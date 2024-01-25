package com.codebees.librasync.dto;


import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;

import com.codebees.librasync.util.Constants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRequestDTO
{
	@NotBlank(message =Constants.ID_VALIDATION)
	private String id;
	@DecimalMin(value = "1", inclusive = true, message = Constants.MIN_VALIDATION)
    @DecimalMax(value = "1.7976931348623157E308", inclusive = true, message = Constants.MAX_VALIDATION)
	private double price;
	@NotBlank(message ="oops! Need to select 'Discount or Tax' for calculation")
	private String discountOrTax;
	@DecimalMin(value = "1", inclusive = true, message = Constants.MIN_VALIDATION)
    @DecimalMax(value = "1.7976931348623157E308", inclusive = true, message = Constants.MAX_VALIDATION)
	private double appliedPercent;
	
	
}