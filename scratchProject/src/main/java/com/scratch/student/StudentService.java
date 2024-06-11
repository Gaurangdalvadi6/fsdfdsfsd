package com.scratch.student;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class StudentService {

	private final StudentRepository repository;

	private final StudentMapper studentMapper;

	public StudentService(StudentRepository repository, StudentMapper studentMapper) {
		super();
		this.repository = repository;
		this.studentMapper = studentMapper;
	}
	
	public StudentResponseDto saveStudent(StudentDto dto) {
		var student = studentMapper.toStudent(dto);
		var savedStudent = repository.save(student);
		return studentMapper.toStudentResponseDto(savedStudent);
	}
	
	public List<StudentResponseDto> findAllStudent() {
		return repository.findAll().stream().map(studentMapper::toStudentResponseDto).collect(Collectors.toList());
	}
	
	public StudentResponseDto findStudentById(Integer id) {
		Student student = repository.findById(id).orElse(new Student());
		return studentMapper.toStudentResponseDto(student);
	}
	
	public List<StudentResponseDto> findStudentsByName(String name){
		return repository.findAllByFirstNameContaining(name)
				.stream()
				.map(studentMapper::toStudentResponseDto)
				.collect(Collectors.toList());
	}
	
	public void deleteStudent(Integer id) {
		repository.deleteById(id);
	}
}
