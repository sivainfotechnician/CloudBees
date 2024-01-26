package com.cloudbees.ecommerce.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.cloudbees.ecommerce.dao.ProductRepository;
import com.cloudbees.ecommerce.dto.CreateRequestDTO;
import com.cloudbees.ecommerce.dto.UpdateRequestDTO;
import com.cloudbees.ecommerce.dto.UpdateResponseDTO;
import com.cloudbees.ecommerce.dto.ComputeRequestDTO;
import com.cloudbees.ecommerce.model.Product;
import com.cloudbees.ecommerce.util.Constants;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Service
public class ProductService {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
	
	@Autowired
	private ProductRepository productRepository;
	
	public ResponseEntity<?> create(CreateRequestDTO request) throws DataAccessException,Exception
	{
		logger.info("Enter into create service");
		 try {
			 	UUID uuid = UUID.randomUUID();
			 	
		        Product productDetailsFromRequest  = new Product(uuid.toString(), request.getName(), request.getDescription(),
		        		request.getQuantityAvailable(), request.getPrice() );
		        		Product response	=	productRepository.save(productDetailsFromRequest);
		        		
		        return new ResponseEntity<>(response, HttpStatus.OK);
		    } 
		 finally
		 {
			 logger.info("Exit from create service");
		 }
		
	}
	
	
	public ResponseEntity<?> getById(String id)  throws Exception
	{
		logger.info("Enter into getById"+id);
		
		
		try 
		{
	            Product response = productRepository.findById(id).orElse(null);
	            if (response != null) 
	            {
	                return new ResponseEntity<>(response, HttpStatus.OK);
	            } 
	            else 
	            {
	            	return new ResponseEntity<>(Constants.UNAVAILABLE, HttpStatus.INTERNAL_SERVER_ERROR);
	            }
	       
	    } 
		finally
		{
			logger.info("Exit from getById service");
		}
		
	}
	
	public ResponseEntity<?> getAll() throws DataAccessException,Exception
	{
		logger.info("Enter into getAll service");
		
		 try 
		 {
	            List<Product> response = productRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
	            return new ResponseEntity<>(response, HttpStatus.OK);
	        } 
		 finally
		 {
			 logger.info("Exit from getCatalog service");
		 }
		
		
	}
	
	public ResponseEntity<?>  update(UpdateRequestDTO request)  throws IllegalArgumentException,DataIntegrityViolationException,Exception
	{
		logger.info("Enter into update service"+request);
		try 
		{
	        Optional<Product> result = productRepository.findById(request.getId());
	        if (result.isPresent())
	        {
	            Product productExistingDetail = result.get();
	            productExistingDetail.setDescription(request.getDescription());
	            productExistingDetail.setName(request.getName());
	            productExistingDetail.setPrice(request.getPrice());
	            productExistingDetail.setQuantityAvailable(request.getQuantityAvailable());
	            productRepository.save(productExistingDetail);
	            
	            return new ResponseEntity<>(Constants.SUCCESS_MESSAGE, HttpStatus.OK);
	        } 
	        else 
	        {
	        	return new ResponseEntity<>(Constants.FAIL_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    } 
		finally
		{
			logger.info("Exit from update service");
		}

	}
	
	public ResponseEntity<?> calculateAndUpdatePrice(ComputeRequestDTO request) throws IllegalArgumentException,DataIntegrityViolationException,Exception
	{
		logger.info("Enter into calculateAndUpdatePrice"+request);
		BigDecimal updatedPrice = BigDecimal.ZERO;
		try 
		{
            Optional<Product> result = productRepository.findById(request.getId());
            if (result.isPresent()) 
            {
                Product productExistingDetail = result.get();
                
                if (Constants.TYPE_DISCOUNT.equalsIgnoreCase(request.getDiscountOrTax())) 
                {                	
                	BigDecimal existingPrice = productExistingDetail.getPrice();
                	BigDecimal percent = BigDecimal.valueOf(request.getAppliedPercent()).divide(BigDecimal.valueOf(100));
                	BigDecimal priceIncrease = existingPrice.multiply(percent);
                	updatedPrice = existingPrice.subtract(priceIncrease);

                } 
                else 
                {
                	
                	BigDecimal existingPrice = productExistingDetail.getPrice();
                	BigDecimal percent = BigDecimal.valueOf(request.getAppliedPercent()).divide(BigDecimal.valueOf(100));
                	BigDecimal priceIncrease = existingPrice.multiply(percent);
                	updatedPrice = existingPrice.add(priceIncrease);
                }
                productExistingDetail.setPrice(updatedPrice);
                productRepository.save(productExistingDetail);
                
                UpdateResponseDTO response = new UpdateResponseDTO(Constants.SUCCESS_MESSAGE, productExistingDetail);
                return new ResponseEntity<>(response, HttpStatus.OK);

            } 
            else 
            {
            	return new ResponseEntity<>(Constants.FAIL_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } 
		finally
		{
			logger.info("Exit from calculateAndUpdatePrice");
		}
		
	}
	
	public ResponseEntity<?> delete(String id) throws Exception
	{
		logger.info("Enter into delete"+id);
		try
		{
			Optional<Product> result = productRepository.findById(id);
			if(result.isPresent())
			{
				productRepository.deleteById(id);
				 return new ResponseEntity<>(Constants.SUCCESS_MESSAGE, HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<>(Constants.FAIL_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		finally
		{
			logger.info("Exit from delete");
		}
		
	}

}
