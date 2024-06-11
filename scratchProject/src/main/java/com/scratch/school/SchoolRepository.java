package com.scratch.school;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scratch.school.School;

public interface SchoolRepository extends JpaRepository<School, Integer>{

	
}
