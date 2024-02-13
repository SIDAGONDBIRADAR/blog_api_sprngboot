package com.biradar.sidagond.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biradar.sidagond.dto.CategoryDto;
import com.biradar.sidagond.exceptions.CategoryNotFoundException;
import com.biradar.sidagond.exceptions.Invalidparameter;
import com.biradar.sidagond.model.Category;
import com.biradar.sidagond.repository.CategoryRepository;
import com.biradar.sidagond.services.CategoryService;

import io.micrometer.common.util.StringUtils;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public Category createCategory(CategoryDto categoryDto) {
		Category category = new Category();
		category.setTitle(categoryDto.getTitle());
		category.setDescription(categoryDto.getDescription());
		return categoryRepository.save(category);
	}

	@Override
	public Category getCategoryById(Integer categroyId) throws Exception {
		if (StringUtils.isBlank(String.valueOf(categroyId))) {
			throw new Invalidparameter("category Id cannot be null");
		}
		Category category = categoryRepository.findByCategoryId(categroyId);
		if (category == null) {
			throw new CategoryNotFoundException("Category not found with Id : " + categroyId);
		} else {
			return category;
		}
	}

	@Override
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	@Override
	public Category updateCategory(CategoryDto categoryDto, Integer categoryId) throws Exception {
		if (StringUtils.isBlank(String.valueOf(categoryId))) {
			throw new Invalidparameter("category Id cannot be null");
		}
		Category category = categoryRepository.findByCategoryId(categoryId);
		if (category == null) {
			throw new CategoryNotFoundException("Category not found with Id : " + categoryId);
		} else {
			category.setTitle(categoryDto.getTitle());
			category.setDescription(categoryDto.getDescription());
			return categoryRepository.save(category);
		}
	}

	@Override
	public void deleteCategory(Integer categoryId) throws Exception {
		if (StringUtils.isBlank(String.valueOf(categoryId))) {
			throw new Invalidparameter("category Id cannot be null");
		}
		Category category = categoryRepository.findByCategoryId(categoryId);
		if (category == null) {
			throw new CategoryNotFoundException("Category not found with Id : " + categoryId);
		} else {
			categoryRepository.delete(category);
		}
	}

}
