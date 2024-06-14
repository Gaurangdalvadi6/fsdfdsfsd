package com.example.mapping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.mapping.models.Author;

import jakarta.transaction.Transactional;


public interface AuthorRepository extends JpaRepository<Author,Integer>{

	@Modifying
	@Transactional
	@Query("update Author a set a.age= :age where a.id= :id")
	int updateAuthor(int id,int age);
	
	@Modifying
	@Transactional
	@Query("update Author a set a.age= :age")
	void updateAllAuthors(int age);
	
}
