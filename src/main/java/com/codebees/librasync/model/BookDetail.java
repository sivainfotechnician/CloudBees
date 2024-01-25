package com.codebees.librasync.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="catalog")
public class BookDetail
{
	@Id
	private String id;
	@Column(name = "name")
	private String name;
	@Column(name = "description")
	private String description;
	@Column(name = "quantity")
	private long quantityAvailable;
	@Column(name = "price")
	private double price;
	@Column(name = "discount_tax")
	private String discountOrTax;
	@Column(name = "applied_percent")
	private double appliedPercent;
	@CreationTimestamp
    @Column(name = "added_at", updatable = false)
	private Timestamp addedAt;
	@CreationTimestamp
    @Column(name = "edited_at")
	private Timestamp editedAt;
	
}