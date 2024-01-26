package com.cloudbees.ecommerce.controller;


import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.cloudbees.ecommerce.dto.CreateRequestDTO;
import com.cloudbees.ecommerce.dto.UpdateRequestDTO;
import com.cloudbees.ecommerce.dto.ComputeRequestDTO;
import com.cloudbees.ecommerce.service.ProductService;


@RestController
@RequestMapping(value = "/ecommerce")
public class ProductController
{
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("/product/create")
    public ResponseEntity<?> create(@RequestBody @Valid CreateRequestDTO request) throws DataAccessException, Exception
	{
		logger.info("Enter into create  controller"+request);
		return productService.create(request);
			
    }
	
	@PutMapping("/product/update") 
    public ResponseEntity<?> update(@RequestBody @Valid UpdateRequestDTO request) throws DataIntegrityViolationException, IllegalArgumentException, Exception 
	{
		logger.info("Enter into update  controller"+request);
		return productService.update(request);
			
    }
	
	@PostMapping("/product/calculateandupdate") 
    public ResponseEntity<?> calculateAndUpdatePrice(@RequestBody @Valid ComputeRequestDTO request) throws DataIntegrityViolationException, IllegalArgumentException, Exception 
	{
		logger.info("Enter into update  controller"+request);
		return productService.calculateAndUpdatePrice(request);
			
    }
	
	@GetMapping("/product/getById/{id}") 
    public ResponseEntity<?> getById(@PathVariable String id) throws Exception 
	{
		logger.info("Enter into get  controller"+id);
		return productService.getById(id);
			
    }
	
	@GetMapping("/product/getAll")
    public ResponseEntity<?> getAll() throws DataAccessException, Exception
	{
		logger.info("Enter into getAll  controller");
		return productService.getAll();
			
    }
	
	@DeleteMapping("/product/deleteById/{id}") 
    public ResponseEntity<?> delete(@PathVariable String id) throws Exception 
	{
		logger.info("Enter into delete  controller");
		return  productService.delete(id);
			
    }
	
}