package com.product.controller;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.exception.ResourceNotFoundException;
import com.product.payload.ApiResponse;
import com.product.payload.ProductDto;
import com.product.service.ProductService;

@RestController
@RequestMapping("/Product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@PostMapping("/{sId}")
	public ResponseEntity<ProductDto> registerProduct(@RequestBody ProductDto productDto, @PathVariable Long sId) {
		try {
			ProductDto registerProduct = this.productService.registerProduct(productDto, sId);
			return new ResponseEntity<>(registerProduct, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	@PutMapping("/{sId}/product/{pId}")
	public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto, @PathVariable Long sId,
			@PathVariable Long pId) {
		try {
			ProductDto updateProduct = this.productService.updateProduct(productDto, pId, sId);
			return new ResponseEntity<>(updateProduct,HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}	
	}
	
	@GetMapping("/")
	public ResponseEntity<List<ProductDto>> getAllProduct(){
		try {
			List<ProductDto> allProduct = this.productService.getAllProduct();
			return new ResponseEntity<List<ProductDto>>(allProduct,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping("/{sellerId}")
	public ResponseEntity<List<ProductDto>> getAllProductBySellerId(@PathVariable Long sellerId){
			List<ProductDto> allProductBySId = this.productService.getAllProductBySId(sellerId);
			return new ResponseEntity<List<ProductDto>>(allProductBySId,HttpStatus.OK);
	}
	
	@GetMapping("/products/{pId}")
	public ResponseEntity<ProductDto> getProductByProductId(@PathVariable Long pId){
			ProductDto productByPId = this.productService.getProductByPId(pId);
			return new ResponseEntity<ProductDto>(productByPId,HttpStatus.OK);
	}
	@DeleteMapping("/products/{pId}")
	public ApiResponse deleteProduct(@PathVariable Long pId) {
		this.productService.deleteProduct(pId);
		return new ApiResponse("Product Deleted Successfully!!!", true);
	}
}
