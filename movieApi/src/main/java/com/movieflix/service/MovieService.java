package com.movieflix.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.movieflix.dto.MovieDto;

@Service
public interface MovieService {

	MovieDto addMovie(MovieDto movieDto,MultipartFile file) throws IOException;
	
	MovieDto getMovie(Integer movieId);
	
	List<MovieDto> getAllMovie();
	
	
}
