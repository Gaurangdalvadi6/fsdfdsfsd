package com.gaurang.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gaurang.blog.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{

}
