package com.cloudbees.ecommerce.controller;


import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.core.JsonParseException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloudbees.ecommerce.dto.ProductCreateRequestDTO;
import com.cloudbees.ecommerce.dto.ProductUpdateRequestDTO;
import com.cloudbees.ecommerce.dto.ProductComputeRequestDTO;
import com.cloudbees.ecommerce.service.ProductService;


@RestController
@RequestMapping(value = "/ecommerce")
public class ProductController
{
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("/product")
    public ResponseEntity<?> create(@RequestBody @Valid ProductCreateRequestDTO request) throws DataAccessException,JsonParseException, Exception
	{
		logger.info("Enter into create  controller"+request);
		return productService.create(request);
			
    }
	
	@PutMapping("/product") 
    public ResponseEntity<?> update(@RequestBody @Valid ProductUpdateRequestDTO request) throws DataIntegrityViolationException, IllegalArgumentException,JsonParseException, Exception 
	{
		logger.info("Enter into update  controller"+request);
		return productService.update(request);
			
    }
	
	@PostMapping("/product/calculatePrice") 
    public ResponseEntity<?> calculatePrice(@RequestBody @Valid ProductComputeRequestDTO request) throws DataIntegrityViolationException, IllegalArgumentException,JsonParseException, Exception 
	{
		logger.info("Enter into update  controller"+request);
		return productService.calculatePrice(request);
			
    }
	
	@GetMapping("/product/{id}") 
    public ResponseEntity<?> getById(@PathVariable String id) throws Exception 
	{
		logger.info("Enter into get  controller"+id);
		return productService.getById(id);
			
    }
	
	@GetMapping("/product")
    public ResponseEntity<?> getAll() throws DataAccessException, Exception
	{
		logger.info("Enter into getAll  controller");
		return productService.getAll();
			
    }
	
	@DeleteMapping("/product/{id}") 
    public ResponseEntity<?> delete(@PathVariable String id) throws Exception 
	{
		logger.info("Enter into delete  controller");
		return  productService.delete(id);
			
    }
	
}