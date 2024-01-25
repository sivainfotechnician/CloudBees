package com.codebees.librasync.controller;


import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codebees.librasync.dto.AddRequestDTO;
import com.codebees.librasync.dto.EditRequestDTO;
import com.codebees.librasync.dto.UpdateRequestDTO;
import com.codebees.librasync.service.CatalogService;


@RestController
@RequestMapping(value = "/librasync")
public class CatalogController
{
	
	private static final Logger logger = LoggerFactory.getLogger(CatalogController.class);
	
	@Autowired
	private CatalogService catalogService;
	
	@PostMapping("/catalog/addBook")
    public ResponseEntity<?> create(@RequestBody @Valid AddRequestDTO request)
	{
		logger.info("Enter into create  controller"+request);
		return catalogService.addBook(request);
			
    }
	
	@PostMapping("/catalog/editBookDetail") 
    public ResponseEntity<?> edit(@RequestBody @Valid EditRequestDTO request) 
	{
		logger.info("Enter into edit  controller"+request);
		return catalogService.editBookDetail(request);
			
    }
	
	@PostMapping("/catalog/updateBookPrice") 
    public ResponseEntity<?> update(@RequestBody @Valid UpdateRequestDTO request) 
	{
		logger.info("Enter into update  controller"+request);
		return catalogService.updateBookPrice(request);
			
    }
	
	@GetMapping("/catalog/getBook/{id}") 
    public ResponseEntity<?> get(@PathVariable String id) 
	{
		logger.info("Enter into get  controller"+id);
		return catalogService.getBookDetail(id);
			
    }
	
	@GetMapping("/catalog/get")
    public ResponseEntity<?> getCatalog()
	{
		logger.info("Enter into getCatalog  controller");
		return catalogService.getCatalog();
			
    }
	
	@DeleteMapping("/catalog/deleteBook/{id}") 
    public ResponseEntity<?> deleteBook(@PathVariable String id) 
	{
		logger.info("Enter into deleteBook  controller");
		return  catalogService.deleteBook(id);
			
    }
	
}