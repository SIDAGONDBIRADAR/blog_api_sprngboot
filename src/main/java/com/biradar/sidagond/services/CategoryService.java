package com.biradar.sidagond.services;

import java.util.List;

import com.biradar.sidagond.dto.CategoryDto;
import com.biradar.sidagond.model.Category;

public interface CategoryService {

	public Category createCategory(CategoryDto categoryDto);

	public Category getCategoryById(Integer categroyId) throws Exception;

	public List<Category> getAllCategories();

	public Category updateCategory(CategoryDto categoryDto, Integer categoryId) throws Exception;

	public void deleteCategory(Integer categoryId) throws Exception;
}
