package com.scratch;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class StudentProfile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String bio;
	
	@OneToOne
	@JoinColumn(name = "student_id")
	private Student student;

	public StudentProfile() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StudentProfile(String bio) {
		super();
		this.bio = bio;
	}
	
	
}
