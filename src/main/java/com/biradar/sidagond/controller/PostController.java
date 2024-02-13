package com.biradar.sidagond.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.biradar.sidagond.dto.PaginationParam;
import com.biradar.sidagond.dto.PostDto;
import com.biradar.sidagond.dto.PostResponse;
import com.biradar.sidagond.model.Post;
import com.biradar.sidagond.services.PostService;

@RestController
@RequestMapping("/post")
public class PostController {

	@Autowired
	PostService postService;

	@PostMapping("/createPost")
	public ResponseEntity<Post> createPost(@RequestBody PostDto postDto) throws Exception {
		Post createdPost = postService.createPost(postDto);
		return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
	}

	@PutMapping("/updatePost")
	public ResponseEntity<Post> updatePost(@RequestBody PostDto postDto) throws Exception {
		Post updatedPost = postService.updatePost(postDto);
		return new ResponseEntity<>(updatedPost, HttpStatus.CREATED);
	}

	@GetMapping("/postsByUser")
	public ResponseEntity<List<Post>> getPostsByUser(@RequestBody PostDto postDto) throws Exception {
		List<Post> postsByUser = postService.getAllPostOfUser(postDto);
		return new ResponseEntity<>(postsByUser, HttpStatus.OK);
	}

	@GetMapping("/postsByCategory")
	public ResponseEntity<List<Post>> getPostsByCategory(@RequestBody PostDto postDto) throws Exception {
		List<Post> postsByCategory = postService.getAllPostOfCategory(postDto);
		return new ResponseEntity<>(postsByCategory, HttpStatus.OK);
	}

	@GetMapping("/allPosts")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam (value="pageNumber",defaultValue = "0",required = false) Integer pageNumber,
			@RequestParam (value="pageSize",defaultValue = "3",required = false) Integer pageSize,
			@RequestParam (value="sortBy",defaultValue = "postId",required = false) String sortBy,
			@RequestParam (value="sortDir",defaultValue = "asc",required = false) String sortDir
			)throws Exception {
		PostResponse allPosts = postService.getAllPosts(pageNumber,pageSize,sortBy,sortDir);
		return new ResponseEntity<>(allPosts, HttpStatus.OK);
	}

	@GetMapping("/postId")
	public ResponseEntity<Post> getPostByPostId(@RequestBody PostDto postDto) throws Exception {
		Post dataBasePost = postService.getPostByPostId(postDto);
		return new ResponseEntity<>(dataBasePost, HttpStatus.OK);
	}

	@DeleteMapping("/deletePost")
	public ResponseEntity<String> deletePost(@RequestBody PostDto postDto) throws Exception {
		postService.deletePost(postDto);
		return new ResponseEntity<String>("Successfully deleted Post : " + postDto.getPostId(), HttpStatus.OK);
	}

}
