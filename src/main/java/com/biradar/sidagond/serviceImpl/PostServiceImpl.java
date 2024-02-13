package com.biradar.sidagond.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.biradar.sidagond.dto.PaginationParam;
import com.biradar.sidagond.dto.PostDto;
import com.biradar.sidagond.dto.PostResponse;
import com.biradar.sidagond.exceptions.CategoryNotFoundException;
import com.biradar.sidagond.exceptions.Invalidparameter;
import com.biradar.sidagond.exceptions.PostNotFoundException;
import com.biradar.sidagond.exceptions.UserNotFoundException;
import com.biradar.sidagond.model.Category;
import com.biradar.sidagond.model.Post;
import com.biradar.sidagond.model.User;
import com.biradar.sidagond.repository.CategoryRepository;
import com.biradar.sidagond.repository.PostRepository;
import com.biradar.sidagond.repository.UserRepository;
import com.biradar.sidagond.services.PostService;

import io.micrometer.common.util.StringUtils;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	PostRepository postRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public Post createPost(PostDto postDto) throws Exception {
		User user = userRepository.findByUserId(postDto.getUserId());
		if (user == null) {
			throw new UserNotFoundException("User not found for userId : " + postDto.getUserId());
		}
		Category category = categoryRepository.findByCategoryId(postDto.getCategoryId());
		if (category == null) {
			throw new CategoryNotFoundException("Category not found for category Id : " + postDto.getCategoryId());
		}
		Post newPost = new Post();
		newPost.setTitle(postDto.getTitle());
		newPost.setImageName("default.png");
		newPost.setCreatedDate(new Date());
		newPost.setContent(postDto.getContent());
		newPost.setUser(user);
		newPost.setCategory(category);
		return postRepository.save(newPost);
	}

	@Override
	public Post updatePost(PostDto postDto) throws Exception {
		if (StringUtils.isBlank(String.valueOf(postDto.getPostId()))) {
			throw new Invalidparameter("postId cannot be null");
		}
		Post dataBasePost = postRepository.findByPostId(postDto.getPostId());
		if (dataBasePost == null) {
			throw new PostNotFoundException("Post not found for postId : " + postDto.getPostId());
		}
		dataBasePost.setTitle(postDto.getTitle());
		dataBasePost.setContent(postDto.getContent());
		return postRepository.save(dataBasePost);
	}

	@Override
	public void deletePost(PostDto postDto) throws Exception {
		Post dataBasePost = postRepository.findByPostId(postDto.getPostId());
		if (dataBasePost != null) {
			postRepository.delete(dataBasePost);
		} else {
			throw new PostNotFoundException("Post not found for postId : " + postDto.getPostId());
		}
	}

	@Override
	public PostResponse getAllPosts(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) throws Exception {
		
		Sort sort = (sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
		
		
		Pageable p = PageRequest.of(pageNumber,pageSize,sort);
		Page<Post> pagePosts = postRepository.findAll(p);
		List<Post> allPosts = pagePosts.getContent();
//		if (allPosts.size() == 0) {
//			throw new PostNotFoundException("No Posts are there ");
//		}
		PostResponse postResponse = new PostResponse();
		postResponse.setPostContents(allPosts);
		postResponse.setPageNumber(pagePosts.getNumber());
		postResponse.setPageSize(pagePosts.getSize());
		postResponse.setTotalElements((int) pagePosts.getTotalElements());
		postResponse.setTotalPages(pagePosts.getTotalPages());
		postResponse.setLastPage(pagePosts.isLast());
		return postResponse;
	}

	@Override
	public List<Post> getAllPostOfUser(PostDto postDto) throws Exception {
		if (StringUtils.isBlank(String.valueOf(postDto.getUserId()))) {
			throw new Invalidparameter("User Id cannot be null ");
		}
		User databaseUser = userRepository.findByUserId(postDto.getUserId());
		if (databaseUser == null) {
			throw new UserNotFoundException("User not found with userId : " + postDto.getUserId());
		} else {
			List<Post> postsByUser = postRepository.findByUser(databaseUser);
			return postsByUser;
		}

	}

	@Override
	public List<Post> getAllPostOfCategory(PostDto postDto) throws Exception {
		if (StringUtils.isBlank(String.valueOf(postDto.getCategoryId()))) {
			throw new Invalidparameter("Category Id cannot be null ");
		}
		Category databaseCategory = categoryRepository.findByCategoryId(postDto.getCategoryId());
		if (databaseCategory == null) {
			throw new CategoryNotFoundException("Category not found with categoryId : " + postDto.getCategoryId());
		} else {
			List<Post> postsByCategory = postRepository.findByCategory(databaseCategory);
			return postsByCategory;
		}
	}

	@Override
	public Post getPostByPostId(PostDto postDto) throws Exception {
		if (StringUtils.isBlank(String.valueOf(postDto.getPostId()))) {
			throw new Invalidparameter("Post Id cannot be null ");
		}
		Post databasePost = postRepository.findByPostId(postDto.getPostId());
		if (databasePost != null) {
			return databasePost;

		} else {
			throw new PostNotFoundException("Post not found with post Id : " + postDto.getPostId());
		}
	}

	@Override
	public List<Post> searchPosts(String keyWord) {
		// TODO Auto-generated method stub
		return null;
	}

}
