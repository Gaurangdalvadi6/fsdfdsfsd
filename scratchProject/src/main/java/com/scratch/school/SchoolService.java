package com.scratch.school;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class SchoolService {
	
	public final SchoolMapper schoolMapper;

	public final SchoolRepository schoolRepository;

	public SchoolService(SchoolMapper schoolMapper, SchoolRepository schoolRepository) {
		super();
		this.schoolMapper = schoolMapper;
		this.schoolRepository = schoolRepository;
	}

	public SchoolDto create(SchoolDto dto) {
		var school = this.schoolMapper.toSchool(dto);
		schoolRepository.save(school);
		return dto;
	}
	
	public List<SchoolDto> findAll(){
		return schoolRepository.findAll().stream().map(schoolMapper::toSchoolDto).collect(Collectors.toList());
	}
}
