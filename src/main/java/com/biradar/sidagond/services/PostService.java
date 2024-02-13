package com.biradar.sidagond.services;

import java.util.List;

import com.biradar.sidagond.dto.PostDto;
import com.biradar.sidagond.dto.PostResponse;
import com.biradar.sidagond.model.Post;

public interface PostService {

	public Post createPost(PostDto postDto) throws Exception;

	public Post updatePost(PostDto postDto) throws Exception;

	public void deletePost(PostDto postDto) throws Exception;

	public PostResponse getAllPosts(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) throws Exception;

	public List<Post> getAllPostOfUser(PostDto postDto) throws Exception;

	public List<Post> getAllPostOfCategory(PostDto postDto) throws Exception;

	public Post getPostByPostId(PostDto postDto) throws Exception;

	public List<Post> searchPosts(String keyWord);

}
