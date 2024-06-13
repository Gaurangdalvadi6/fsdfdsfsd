package com.example.mapping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mapping.models.Author;


public interface AuthorRepository extends JpaRepository<Author,Integer>{


}
