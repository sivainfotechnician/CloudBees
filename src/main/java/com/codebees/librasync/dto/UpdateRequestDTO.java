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
	@NotBlank(message = Constants.SELECT_TYPE_MESSAGE)
	private String discountOrTax;
	@DecimalMin(value = "1", inclusive = true, message = Constants.MIN_VALIDATION)
    @DecimalMax(value = "100", inclusive = true, message = Constants.MAX_VALIDATION_FOR_PERCENT)
	private double appliedPercent;
	
	
}