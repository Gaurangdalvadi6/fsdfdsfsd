package com.scratch.student;

import org.springframework.stereotype.Service;

import com.scratch.school.School;

@Service
public class StudentMapper {

	public Student toStudent(StudentDto dto) {
		var student = new Student();
		student.setFirstName(dto.firstName());
		student.setLastName(dto.lastName());
		student.setEmail(dto.email());
		
		var school = new School();
		school.setId(dto.schoolId());
		
		student.setSchool(school);
		
		return student;
	}
	
	public StudentResponseDto toStudentResponseDto(Student student) {
		return new StudentResponseDto(student.getFirstName(), student.getLastName(), student.getEmail());
	}
}
