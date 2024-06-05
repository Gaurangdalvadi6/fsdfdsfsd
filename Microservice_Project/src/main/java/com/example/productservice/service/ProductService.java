package com.example.productservice.service;

import com.example.productservice.dto.ProductDTO;
import com.example.productservice.exception.ResourceNotFoundException;
import com.example.productservice.mapper.ProductMapper;
import com.example.productservice.model.Product;
import com.example.productservice.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;

    public Page<ProductDTO> getAllProducts(Pageable pageable) {
        logger.info("Fetching all products");
        return productRepository.findAll(pageable).map(ProductMapper::toProductDTO);
    }

    public Optional<ProductDTO> getProductById(Long id) {
        logger.info("Fetching product with id: {}", id);
        return productRepository.findById(id).map(ProductMapper::toProductDTO);
    }

    public ProductDTO createProduct(ProductDTO productDTO) {
        logger.info("Creating new product with name: {}", productDTO.getName());
        Product product = ProductMapper.toProduct(productDTO);
        return ProductMapper.toProductDTO(productRepository.save(product));
    }

    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        logger.info("Updating product with id: {}", id);
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        return ProductMapper.toProductDTO(productRepository.save(product));
    }

    public void deleteProduct(Long id) {
        logger.info("Deleting product with id: {}", id);
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        productRepository.delete(product);
    }
}
