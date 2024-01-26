package com.cloudbees.ecommerce.configuration;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cloudbees.ecommerce.util.Constants;
import com.fasterxml.jackson.core.JsonParseException;


@RestControllerAdvice
public class ApplicationExceptionHandler
{
	
	private static final Logger logger = LoggerFactory.getLogger(ApplicationExceptionHandler.class);
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleInvalidInput(MethodArgumentNotValidException ex)
	{
		Map<String,String> errorMap = new HashMap<String,String>();
		
		ex.getBindingResult().getFieldErrors().forEach(error -> {errorMap.put(error.getField(),error.getDefaultMessage());});
		
		return new ResponseEntity<>(errorMap, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(DataAccessException.class)
    public ResponseEntity<String> handleDataAccessException(DataAccessException e) 
	{
		logger.error("DataAccessException occurred: " + e.getMessage());

        return new ResponseEntity<>(Constants.ACCESSING_DATA_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
    }


	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) 
	{
        logger.error("IllegalArgumentException occurred: " + e.getMessage());

        return new ResponseEntity<>(Constants.INVALID_REQUEST, HttpStatus.BAD_REQUEST);
    }
	
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(JsonParseException.class)
    public ResponseEntity<String> handleJsonParseException(JsonParseException e) 
	{
        logger.error("JsonParseException occurred: " + e.getMessage());

        return new ResponseEntity<>(Constants.INVALID_REQUEST, HttpStatus.BAD_REQUEST);
    }

	@ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException e) 
	{
        logger.error("DataIntegrityViolationException occurred: " + e.getMessage());

        return new ResponseEntity<>(Constants.DATA_INTEGRITY_VIOLATION_ERROR, HttpStatus.CONFLICT);
    }
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception e) 
	{
        logger.error("General Exception occurred: " + e.getMessage());

        return new ResponseEntity<>(Constants.UNEXPECTED_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}