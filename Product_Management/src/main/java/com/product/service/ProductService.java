package com.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.product.payload.ProductDto;
import com.product.repository.ProductResponse;

@Service
public interface ProductService {

	ProductDto registerProduct(ProductDto productDto,Long sId);
	
	ProductDto updateProduct(ProductDto productDto,Long pId,Long sId);
	
	ProductResponse getAllProduct(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
	
	List<ProductDto> getAllProductBySId(Long sId);
	
	ProductDto getProductByPId(Long pId);
	
	void deleteProduct(Long pId);
}
