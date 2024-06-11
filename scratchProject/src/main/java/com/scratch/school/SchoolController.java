package com.scratch.school;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
//@RequestMapping("/school")
public class SchoolController {

	private final SchoolService schoolService;

	public SchoolController(SchoolService schoolService) {
		super();
		this.schoolService = schoolService;
	}



	@PostMapping("/schools/")
	public SchoolDto create(@RequestBody SchoolDto dto) {
		return schoolService.create(dto);
	}

	@GetMapping("/schools")
	public List<SchoolDto> findAll(){
		return schoolService.findAll();
	}
	
	
}
