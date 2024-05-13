package com.gaurang.blog.services;

import java.util.List;

import com.gaurang.blog.payloads.CategoryDto;

public interface CategoryService {

	// create
	CategoryDto createCategory(CategoryDto categoryDto);
	
	// update
	CategoryDto updateCategory(CategoryDto categoryDto,int categoryId);
	
	// delete
	void deleteCategory(int categoryId);
	
	// get
	CategoryDto getCategory(int categoryId);
	
	// get All
	List<CategoryDto> getCategories();
}
