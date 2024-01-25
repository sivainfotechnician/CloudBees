package com.codebees.librasync.service;

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

import com.codebees.librasync.dao.CatalogRepository;
import com.codebees.librasync.dto.AddRequestDTO;
import com.codebees.librasync.dto.EditRequestDTO;
import com.codebees.librasync.dto.UpdateRequestDTO;
import com.codebees.librasync.model.BookDetail;
import com.codebees.librasync.util.Constants;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Service
public class CatalogService {
	
	private static final Logger logger = LoggerFactory.getLogger(CatalogService.class);
	
	@Autowired
	private CatalogRepository catlogRepository;
	
	public ResponseEntity<?> addBook(AddRequestDTO request)
	{
		logger.info("Enter into addBook service");
		 try {
			 	UUID uuid = UUID.randomUUID();
			 	logger.info("uuid->"+uuid);
		        BookDetail bookDetailFromRequest  = new BookDetail(uuid.toString(), request.getName(), request.getDescription(),
		        		request.getQuantityAvailable(), request.getPrice(), "", 0,
		                Timestamp.from(Instant.now()), Timestamp.from(Instant.now()));
		        logger.info("bookDetailFromRequest->"+bookDetailFromRequest);
		        		BookDetail response	=	catlogRepository.save(bookDetailFromRequest);
		        		logger.info("response->"+response);
		        return new ResponseEntity<>(response, HttpStatus.OK);
		    } 
		 catch (DataAccessException e) 
		 {
		        logger.info("DataAccessException in addBook service"+e.getMessage());
		        return new ResponseEntity<>(Constants.FAIL_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
		 } 
		 catch (Exception e) 
		 {
			 logger.info("GeneralException in addBook service"+e.getMessage());
			 return new ResponseEntity<>(Constants.FAIL_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
		 }
		 finally
		 {
			 logger.info("Exit from addBook service");
		 }
		
	}
	
	
	@Cacheable(value="catalog-cache", key = "#id")
	public ResponseEntity<?> getBookDetail(String id) 
	{
		logger.info("Enter into getBookDetail"+id);
		
		
		try 
		{
	            BookDetail response = catlogRepository.findById(id).orElse(null);
	            if (response != null) 
	            {
	            	logger.info("Inside If");
	                return new ResponseEntity<>(response, HttpStatus.OK);
	            } 
	            else 
	            {
	            	logger.info("Inside else");
	            	return new ResponseEntity<>(Constants.UNAVAILABLE, HttpStatus.INTERNAL_SERVER_ERROR);
	            }
	       
	    } 
		catch (Exception e) 
		{
			logger.info("GeneralException in getBookDetail service"+e.getMessage());
			return new ResponseEntity<>(Constants.FAIL_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
		finally
		{
			logger.info("Exit from getBookDetail service");
		}
		
	}
	
	public ResponseEntity<?> getCatalog()
	{
		logger.info("Enter into getCatalog service");
		 try {
	            List<BookDetail> response = catlogRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
	            logger.info("response->"+response);
	            return new ResponseEntity<>(response, HttpStatus.OK);
	        } 
		 catch (DataAccessException e) 
		 {
			 logger.info("DataAccessException in getCatalog service"+e.getMessage());
			 return new ResponseEntity<>(Constants.UNAVAILABLE, HttpStatus.INTERNAL_SERVER_ERROR);
	        } 
		 catch (Exception e) 
		 {
			 logger.info("GeneralException in getCatalog service"+e.getMessage());
			 return new ResponseEntity<>(Constants.UNAVAILABLE, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
		 finally
		 {
			 logger.info("Exit from getCatalog service");
		 }
		
		
	}
	
	@CachePut(value = "catalog-cache", key = "#request.id")
	public ResponseEntity<?>  editBookDetail(EditRequestDTO request) 
	{
		logger.info("Enter into editBookDetail service"+request);
		try 
		{
	        Optional<BookDetail> result = catlogRepository.findById(request.getId());
	        if (result.isPresent())
	        {
	        	logger.info("Inside If");
	            BookDetail bookExistingDetail = result.get();
	            bookExistingDetail.setDescription(request.getDescription());
	            bookExistingDetail.setName(request.getName());
	            bookExistingDetail.setPrice(request.getPrice());
	            bookExistingDetail.setQuantityAvailable(request.getQuantityAvailable());
	            BookDetail response= catlogRepository.save(bookExistingDetail);
	            logger.info("response->"+response);
	            return new ResponseEntity<>(response, HttpStatus.OK);
	        } 
	        else 
	        {
	        	return new ResponseEntity<>(Constants.UNAVAILABLE, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    } 
		catch (IllegalArgumentException | DataIntegrityViolationException e) 
		{
			logger.info("IllegalArgumentException or DataIntegrityViolationException in editBookDetail"+e.getMessage());
			return new ResponseEntity<>(Constants.FAIL_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
	    } 
		catch (Exception e) 
		{
			logger.info("GeneralException in editBookDetail"+e.getMessage());
			return new ResponseEntity<>(Constants.FAIL_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
		finally
		{
			logger.info("Exit from editBookDetail");
		}

	}
	
	@CachePut(value = "catalog-cache", key = "#request.id")
	public ResponseEntity<?> updateBookPrice(UpdateRequestDTO request) 
	{
		logger.info("Enter into updateBookPrice"+request);
		try 
		{
            Optional<BookDetail> result = catlogRepository.findById(request.getId());
            if (result.isPresent()) 
            {
            	logger.info("Inside If");
                BookDetail bookExistingDetail = result.get();
                double updatedPrice = 0;

                if (Constants.TYPE_DISCOUNT.equalsIgnoreCase(request.getDiscountOrTax())) {
                	logger.info("Inside discount calculation :"+bookExistingDetail.getPrice()+" "+request.getAppliedPercent());
                    updatedPrice = (bookExistingDetail.getPrice() - (bookExistingDetail.getPrice() * (request.getAppliedPercent() / 100)));
                } else {
                	logger.info("Inside tax calculation :"+bookExistingDetail.getPrice()+" "+request.getAppliedPercent());
                    updatedPrice = (bookExistingDetail.getPrice() + (bookExistingDetail.getPrice() * (request.getAppliedPercent() / 100)));
                }
                logger.info("updatedPrice :"+updatedPrice);
                bookExistingDetail.setAppliedPercent(request.getAppliedPercent());
                bookExistingDetail.setDiscountOrTax(request.getDiscountOrTax());
                bookExistingDetail.setPrice(updatedPrice);
                BookDetail response =  catlogRepository.save(bookExistingDetail);
                logger.info("response->"+response);
                 return new ResponseEntity<>(response, HttpStatus.OK);
            } 
            else 
            {
            	return new ResponseEntity<>(Constants.UNAVAILABLE, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } 
		catch (IllegalArgumentException | DataIntegrityViolationException e) 
		{

			logger.info("IllegalArgumentException or DataIntegrityViolationException in updateBookPrice"+e.getMessage());
			return new ResponseEntity<>(Constants.FAIL_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
        } 
		catch (Exception e) 
		{
			logger.info("GeneralException in updateBookPrice"+e.getMessage());
			return new ResponseEntity<>(Constants.FAIL_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
        }
		finally
		{
			logger.info("Exit from updateBookPrice");
		}
		
	}
	
	@CacheEvict(value = "catalog-cache", key = "#id")
	public ResponseEntity<?> deleteBook(String id) 
	{
		logger.info("Enter into deleteBook"+id);
		try
		{
			Optional<BookDetail> result = catlogRepository.findById(id);
			if(result.isPresent())
			{
				logger.info("Inside If");
				catlogRepository.deleteById(id);
				logger.info("After delete method");
				 return new ResponseEntity<>(Constants.SUCCESS_MESSAGE, HttpStatus.OK);
			}
			else
			{
				return new ResponseEntity<>(Constants.UNAVAILABLE, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		catch (Exception e) 
		{
			logger.info("General Exception in  delete method"+e.getMessage());
            return new ResponseEntity<>(Constants.FAIL_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
        }
		finally
		{
			logger.info("Exit from deleteBook");
		}
		
	}

}
