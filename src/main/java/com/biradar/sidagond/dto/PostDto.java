package com.biradar.sidagond.dto;

import jakarta.validation.constraints.Size;

public class PostDto {

	private Integer postId;

	@Size(min = 5, max = 50)
	private String title;

	@Size(min = 50, max = 1000)
	private String content;

	private Integer categoryId;
	private Integer userId;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

}
