package com.cloudbees.ecommerce.util;

public class Constants {
	
	public static final String MAX_VALIDATION = "The value must be less than or equal to the maximum allowed value (" + Double.MAX_VALUE + ")";
	public static final String MAX_VALIDATION_FOR_PERCENT = "The value must be less than or equal to the maximum allowed value 100";
	public static final String MIN_VALIDATION = "The value must be greater than or equal to 1";
	public static final String MAX_VALIDATION_LONG = "The value must be less than or equal to the maximum allowed value (" + Long.MAX_VALUE + ")";
	public static final String NAME_VALIDATION = "Name should not be empty";
	public static final String PRICE_VALIDATION = "price should not be empty";
	public static final String ID_VALIDATION = "ID must not be empty";
	public static final String TYPE_DISCOUNT = "Discount";
	public static final String ID_INVALID = "Invalid product ID";
	public static final String UNAVAILABLE = "No data found";
	public static final String SUCCESS_MESSAGE = "Operation successful!";
	public static final String FAIL_MESSAGE = "Operation failed!";
	public static final String INVALID_REQUEST = "Invalid request data";
	public static final String UNEXPECTED_ERROR = "An unexpected error occurred";
	public static final String ACCESSING_DATA_ERROR ="An error occurred while accessing data";
	public static final String DATA_INTEGRITY_VIOLATION_ERROR ="Data integrity violation occurred";
	public static final String INVALID_TYPE_MESSAGE ="Invalid value ; must be 'Discount' or 'Tax'";
	public static final String INVALID_NAME_PATTERN ="Invalid value; must contain only alphabets and numbers";
	public static final String REGEX_PATTERN ="^[a-zA-Z0-9]+$";

	
}
