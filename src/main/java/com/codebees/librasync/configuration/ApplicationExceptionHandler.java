package com.codebees.librasync.configuration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandler
{
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleInvalidInput(MethodArgumentNotValidException ex)
	{
		Map<String,String> errorMap = new HashMap<String,String>();
		
		ex.getBindingResult().getFieldErrors().forEach(error -> {errorMap.put(error.getField(),error.getDefaultMessage());});
		
		return new ResponseEntity<>(errorMap, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}