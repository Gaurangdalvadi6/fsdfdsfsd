package com.gaurang.blog.services;

import java.util.List;


import com.gaurang.blog.payloads.PostDto;
import com.gaurang.blog.payloads.PostResponse;


public interface PostService {

	// create
	
	PostDto createPost(PostDto postDto,int userId,int categoryId);
	
	// update
	PostDto updatePost(PostDto postDto,int postId);
	
	// delete
	void deletePost(int postId);
	
	// get single Post
	PostDto getPostById(int postId);
	
	//get all
	PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
//	List<PostDto> getAllPost();
	
	// get All Post By Category
	List<PostDto> getPostsByCategory(int categoryId);
	
	// get All posts By User
	List<PostDto> getPostsByUser(int userId);
	
	// search posts
	List<PostDto> searchPosts(String keyword);
}
