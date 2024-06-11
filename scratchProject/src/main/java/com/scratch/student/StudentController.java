package com.scratch.student;

import java.util.HashMap;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
public class StudentController {
	
	private final StudentService studentService;

	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}



	@PostMapping("/students")
	public StudentResponseDto saveStudent(@Valid @RequestBody StudentDto dto) {
		return this.studentService.saveStudent(dto);
	}

	
	
	@GetMapping("/students")
	public List<StudentResponseDto> findAllStudent() {
		return this.studentService.findAllStudent();
	}
	
//	@GetMapping("/students")
//	public List<Student> findAllStudent() {
//		return repository.findAll();
//	}

	@GetMapping("/students/{student-id}")
	public StudentResponseDto findStudentById(@PathVariable("student-id") Integer id) {
		return this.studentService.findStudentById(id);
	}

	@GetMapping("/students/search/{student-name}")
	public List<StudentResponseDto> findStudentsByName(@PathVariable("student-name") String name) {
		return this.studentService.findStudentsByName(name);
	}
	
	@DeleteMapping("/students/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public void deleteStudent(@PathVariable int id) {
		this.studentService.deleteStudent(id);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handelMethodArgumentNotValidException(MethodArgumentNotValidException ex){
		var errors = new HashMap<String,String>();
		ex.getBindingResult().getAllErrors().forEach(error -> {
			var fieldname = ((FieldError)error).getField();
			var errormessage = error.getDefaultMessage();
			errors.put(fieldname, errormessage);
		});
		return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
	}
}

