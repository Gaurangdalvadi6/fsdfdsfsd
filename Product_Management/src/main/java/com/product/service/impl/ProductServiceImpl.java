package com.product.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.exception.ResourceNotFoundException;
import com.product.model.Product;
import com.product.payload.ProductDto;
import com.product.repository.ProductRepo;
import com.product.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ProductRepo productRepo;
	@Override
	public ProductDto registerProduct(ProductDto productDto, Long sId) {
		
		Product product = this.modelMapper.map(productDto, Product.class);
		product.setPId(productDto.getpId());
		product.setSId(sId);
		product.setPName(productDto.getpName());
		product.setPPrice(productDto.getpPrice());
		product.setPImage(productDto.getpImage());
		product.setPCategory(productDto.getpCategory());
		product.setPDescription(productDto.getpDescription());
		Product save = this.productRepo.save(product);
		ProductDto productDto2 = this.modelMapper.map(save, ProductDto.class);
		return productDto2;
	}

	@Override
	public ProductDto updateProduct(ProductDto productDto, Long pId, Long sId) {
		Product product = this.modelMapper.map(productDto, Product.class);
		
		product.setPId(pId);
		product.setSId(sId);
		product.setPName(productDto.getpName());
		product.setPPrice(productDto.getpPrice());
		product.setPImage(productDto.getpImage());
		product.setPCategory(productDto.getpCategory());
		product.setPDescription(productDto.getpDescription());
		Product update = this.productRepo.save(product);
		ProductDto dto = this.modelMapper.map(update, ProductDto.class);
		return dto;
	}

	@Override
	public List<ProductDto> getAllProduct() {
		List<Product> allProduct = this.productRepo.findAll();
		
		List<ProductDto> list = allProduct.stream().map(product -> this.modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
		return list;
	}

	@Override
	public List<ProductDto> getAllProductBySId(Long sId) {
		List<Product> products = this.productRepo.findAllBySId(sId);
		return products.stream().map(product -> modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
	}

	@Override
	public ProductDto getProductByPId(Long pId) {
		Product product = this.productRepo.findById(pId).orElseThrow(() -> new ResourceNotFoundException("product ", "id", pId));
		ProductDto productDto = this.modelMapper.map(product, ProductDto.class);
		return productDto;
	}

	@Override
	public void deleteProduct(Long pId) {
		Product product = this.productRepo.findById(pId).orElseThrow(() -> new ResourceNotFoundException("Product", "id ", pId));
		this.productRepo.delete(product);;
	}
	
	

}
