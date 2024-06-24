package com.movieflix.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.movieflix.dto.MovieDto;
import com.movieflix.entity.Movie;
import com.movieflix.repository.MovieRepository;
import com.movieflix.service.FileService;
import com.movieflix.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService{

	private final MovieRepository movieRepository;
	
	private final FileService fileService;
	
	@Value("${project-poster}")
	private String path;
	
	@Value("${base-url}")
	private String baseUrl;
	
	public MovieServiceImpl(MovieRepository movieRepository, FileService fileService) {
		super();
		this.movieRepository = movieRepository;
		this.fileService = fileService;
	}

	@Override
	public MovieDto addMovie(MovieDto movieDto, MultipartFile file) throws IOException {
		// 1. upload the file 
		String uploadfileName = this.fileService.uploadfile(path, file);
		
		// 2. set the value of field 'poster' as filename 
		movieDto.setPoster(uploadfileName);
		
		// 3. map dto to movie object
		Movie movie = new Movie(movieDto.getMovieId(),
								movieDto.getTitle(),
								movieDto.getDirector(),
								movieDto.getStudio(),
								movieDto.getMovieCast(),
								movieDto.getReleaseYear(),
								movieDto.getPoster());
		
		// 4. save the movie object -> saved movie object
		Movie savedMovie = movieRepository.save(movie);
		
		// 5. generate the url
		String posterUrl = baseUrl + "/file/" + uploadfileName;
		
		// 6. map movie object to dto object and return it
		MovieDto response = new MovieDto(savedMovie.getMovieId(),
										savedMovie.getTitle(),
										savedMovie.getDirector(),
										savedMovie.getStudio(),
										savedMovie.getMovieCast(),
										savedMovie.getReleaseYear(),
										savedMovie.getPoster(),
										posterUrl);
		return response;
	}

	@Override
	public MovieDto getMovie(Integer movieId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MovieDto> getAllMovie() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
