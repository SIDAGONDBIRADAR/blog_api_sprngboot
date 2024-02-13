package com.biradar.sidagond.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biradar.sidagond.model.Category;
import com.biradar.sidagond.model.Post;
import com.biradar.sidagond.model.User;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

	List<Post> findByUser(User user);

	List<Post> findByCategory(Category category);
	

	
	Post findByPostId(Integer postId);

}
