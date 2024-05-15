package com.gaurang.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaurang.blog.entities.Comment;
import com.gaurang.blog.entities.Post;
import com.gaurang.blog.exceptions.ResourceNotFoundException;
import com.gaurang.blog.payloads.CommentDto;
import com.gaurang.blog.repositories.CommentRepo;
import com.gaurang.blog.repositories.PostRepo;
import com.gaurang.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post ", "post Id", postId));
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		
		comment.setPost(post);
		Comment savedComment = this.commentRepo.save(comment);
		CommentDto commentDto2 = this.modelMapper.map(savedComment, CommentDto.class);
		return commentDto2;
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment comment = this.commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("comment ","comment Id" , commentId));
		this.commentRepo.delete(comment);
		
	}

}
