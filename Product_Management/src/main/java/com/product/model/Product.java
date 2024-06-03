package com.product.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
	
	private Long sId;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Long pId;
	
	@Column(name = "product_name")
	private String pName;
	
	@Column(name = "product_price")
	private Long pPrice;
	
	@Column(name = "product_category")	
	private String pCategory;
	
	@Column(name = "product_description")
	private String pDescription;
}
