package com.cloudbees.ecommerce.dto;


import com.cloudbees.ecommerce.model.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateResponseDTO 
{
    private String message;
    private Product product;

}