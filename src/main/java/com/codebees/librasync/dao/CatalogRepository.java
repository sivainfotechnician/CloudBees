package com.codebees.librasync.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codebees.librasync.model.BookDetail;

public interface CatalogRepository extends JpaRepository<BookDetail, String> 
{

}
