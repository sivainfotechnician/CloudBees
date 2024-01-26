package com.cloudbees.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cloudbees.ecommerce.model.Product;

public interface ProductRepository extends JpaRepository<Product, String> 
{

}
