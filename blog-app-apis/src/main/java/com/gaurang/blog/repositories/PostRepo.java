package com.gaurang.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gaurang.blog.entities.Category;
import com.gaurang.blog.entities.Post;
import com.gaurang.blog.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer> {
	
	List<Post> findByUser(User user);
	
	List<Post> findByCategory(Category category);
	
	
	

}
