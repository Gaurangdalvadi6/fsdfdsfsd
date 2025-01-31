package com.gaurang.blog.controllers;

import java.util.List;

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

import com.gaurang.blog.payloads.ApiResponse;
import com.gaurang.blog.payloads.CategoryDto;
import com.gaurang.blog.services.impl.CategoryServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryServiceImpl categoryServiceImpl;

	// create
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCatogory(@Valid @RequestBody CategoryDto categoryDto) {
		CategoryDto category = this.categoryServiceImpl.createCategory(categoryDto);
		return new ResponseEntity<>(category, HttpStatus.CREATED);
	}

	// update
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,
			@PathVariable("categoryId") int categoryId) {
		CategoryDto updateCategory = this.categoryServiceImpl.updateCategory(categoryDto, categoryId);
		return new ResponseEntity<>(updateCategory, HttpStatus.OK);
	}

	// delete
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable int categoryId) {
		this.categoryServiceImpl.deleteCategory(categoryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("catogory is deleted successfully !!", true),
				HttpStatus.OK);
	}

	// get
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable int categoryId)
	{
		CategoryDto category = this.categoryServiceImpl.getCategory(categoryId);
		return new ResponseEntity<CategoryDto>(category,HttpStatus.OK);
	}

	// get All
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategory()
	{
		List<CategoryDto> categories = this.categoryServiceImpl.getCategories();
		return ResponseEntity.ok(categories);
	}
}
