package com.scratch.student;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.scratch.school.School;
import com.scratch.studentprofile.StudentProfile;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "T_STUDENT")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String firstName;
	
	private String lastName;
	
	
	
	@Column(unique = true)
	private String email;
	
	private int age;
	
	@ManyToOne
	@JoinColumn(name = "school_id")
	@JsonBackReference
	private School school;
	
	@OneToOne(mappedBy = "student",cascade = CascadeType.ALL)
	private StudentProfile studentProfile;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public StudentProfile getStudentProfile() {
		return studentProfile;
	}

	public void setStudentProfile(StudentProfile studentProfile) {
		this.studentProfile = studentProfile;
	}

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(String firstName, String lastName, String email, Integer age) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.age = age;
	}
}
