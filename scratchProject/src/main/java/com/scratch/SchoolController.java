package com.scratch;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/school")
public class SchoolController {

	public final SchoolRepository schoolRepository;

	public SchoolController(SchoolRepository schoolRepository) {
		super();
		this.schoolRepository = schoolRepository;
	}

	@PostMapping("/schools/")
	public SchoolDto create(@RequestBody SchoolDto dto) {
		var school = toSchool(dto);
		schoolRepository.save(school);
		return dto;
	}
	
	

	@GetMapping("/schools")
	public List<SchoolDto> findAll(){
		return schoolRepository.findAll().stream().map(this::toSchoolDto).collect(Collectors.toList());
	}
	
	private School toSchool(SchoolDto dto) {
		return new School(dto.name());
	}
	
	private SchoolDto toSchoolDto(School school) {
		return new SchoolDto(school.getName());
	}
}
