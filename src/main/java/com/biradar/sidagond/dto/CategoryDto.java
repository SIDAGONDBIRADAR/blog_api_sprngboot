package com.biradar.sidagond.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class CategoryDto {

//	private Integer categoryId;
	
	@NotEmpty(message = "Title should have a value")
	@Size(min=3,max = 100,message="Title should be atleast 3 characters")
	private String title;
	
	@NotEmpty(message = "Description should have a value")
	@Size(min=30,max = 1000,message="Description should be atleast 3 characters")
	private String description;

//	public Integer getCategoryId() {
//		return categoryId;
//	}
//
//	public void setCategoryId(Integer categoryId) {
//		this.categoryId = categoryId;
//	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
