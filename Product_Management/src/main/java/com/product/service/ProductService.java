package com.product.service;

import java.util.List;

import com.product.payload.ProductDto;

public interface ProductService {

	ProductDto registerProduct(ProductDto productDto,Long sId);
	
	ProductDto updateProduct(ProductDto productDto,Long pId,Long sId);
	
	List<ProductDto> getAllProduct();
	
	List<ProductDto> getAllProductBySId(Long sId);
	
	ProductDto getProductByPId(Long pId);
	
	void deleteProduct(Long pId);
}
