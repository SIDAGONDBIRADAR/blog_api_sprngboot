package com.biradar.sidagond.controller;

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

import com.biradar.sidagond.dto.CategoryDto;
import com.biradar.sidagond.model.Category;
import com.biradar.sidagond.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	@PostMapping("/create")
	public ResponseEntity<Category> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
		Category createdCategory = categoryService.createCategory(categoryDto);
		return new ResponseEntity<>(createdCategory, HttpStatus.OK);
	}

	@PutMapping("/update/{categoryId}")
	public ResponseEntity<Category> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer categoryId)
			throws Exception {
		Category updatedCategory = categoryService.updateCategory(categoryDto, categoryId);
		return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
	}

	@GetMapping("/{categoryId}")
	public ResponseEntity<Category> getCategoryId(@PathVariable Integer categoryId) throws Exception {
		Category categoryById = categoryService.getCategoryById(categoryId);
		return new ResponseEntity<>(categoryById, HttpStatus.OK);
	}

	@GetMapping("/getAllCategories")
	public ResponseEntity<List<Category>> getAllCategories() {
		List<Category> categories = categoryService.getAllCategories();
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}

	@DeleteMapping("/{categoryId}")
	public ResponseEntity<String> deleteCategoryById(@PathVariable Integer categoryId) throws Exception {
		categoryService.deleteCategory(categoryId);
		return new ResponseEntity<>("Deleted category successfully", HttpStatus.OK);
	}

}
