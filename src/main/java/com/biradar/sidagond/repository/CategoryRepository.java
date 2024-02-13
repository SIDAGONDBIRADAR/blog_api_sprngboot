package com.biradar.sidagond.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biradar.sidagond.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

	public Category findByCategoryId(Integer categoryId);
}
