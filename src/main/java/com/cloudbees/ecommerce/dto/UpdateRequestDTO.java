package com.cloudbees.ecommerce.dto;


import java.math.BigDecimal;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.cloudbees.ecommerce.util.Constants;

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
	
	@NotBlank(message =Constants.TITLE_VALIDATION)
	private String name;
	
	private String description;
	
	@Min(value = 1, message = Constants.MIN_VALIDATION)
    @Max(value = Long.MAX_VALUE, message = Constants.MAX_VALIDATION_LONG)
	private long quantityAvailable;
	
	@DecimalMin(value = "1", inclusive = true, message = Constants.MIN_VALIDATION)
    @DecimalMax(value = "1.7976931348623157E308", inclusive = true, message = Constants.MAX_VALIDATION)
	private BigDecimal price;
	
	
}