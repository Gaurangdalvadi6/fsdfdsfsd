package com.movieflix.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieflix.dto.MovieDto;
import com.movieflix.dto.MoviePageResponse;
import com.movieflix.exceptions.EmptyFileException;
import com.movieflix.service.MovieService;
import com.movieflix.utils.AppConstants;

@RestController
@RequestMapping("/api/v1/movie")
public class MovieController {

	private final MovieService movieService;

	public MovieController(MovieService movieService) {
		super();
		this.movieService = movieService;
	}
	
	@PostMapping("/add-movie")
	public ResponseEntity<MovieDto> addMovieHandler(@RequestPart MultipartFile file,
													@RequestPart String movieDto) throws IOException{
		if (file.isEmpty()) {
			throw new EmptyFileException("File is empty! Please send another file!");
		}
		MovieDto dto = convertMovieDto(movieDto);
		MovieDto addMovie = movieService.addMovie(dto, file);
		
		return new ResponseEntity<>(addMovie,HttpStatus.CREATED);
	}
	
	@GetMapping("/{movieId}")
	public ResponseEntity<MovieDto> getMovieHandler(@PathVariable Integer movieId){
		MovieDto movieDto = movieService.getMovie(movieId);
		return ResponseEntity.ok(movieDto);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<MovieDto>> getAllMoviesHandler(){
		List<MovieDto> allMovie = movieService.getAllMovie();
		return ResponseEntity.ok(allMovie);
	}
	
	@PutMapping("/update/{movieId}")
	public ResponseEntity<MovieDto> updateMovieHandler(@PathVariable Integer movieId,
														@RequestPart MultipartFile file,
														@RequestPart String movieDtoObject) throws IOException{
		if (file.isEmpty()) {
			file = null;
		}
		MovieDto movieDto = convertMovieDto(movieDtoObject);
		return ResponseEntity.ok(movieService.updateMovie(movieId, movieDto, file));
	}
	
	@DeleteMapping("/delete/{movieId}")
	public ResponseEntity<String> deleteMovieHandler(@PathVariable Integer movieId) throws IOException{
		return ResponseEntity.ok(movieService.deleteMovie(movieId));
	}
	
	@GetMapping("/allMoviesPage")
	public ResponseEntity<MoviePageResponse> getMoviesWithPagination(
			@RequestParam(defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
			@RequestParam(defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize
			){
		return ResponseEntity.ok(movieService.getAllMoviesWithPagination(pageNumber, pageSize));
	}
	
	@GetMapping("/allMoviesPageSort")
	public ResponseEntity<MoviePageResponse> getMoviesWithPaginationAndSorting(
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir) {
		return ResponseEntity.ok(movieService.getAllMoviesWithPaginationAndSorting(pageNumber, pageSize, sortBy, sortDir));
	}
	
	// this for convert string into the json format
	private MovieDto convertMovieDto(String movieDtoObject) throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		MovieDto movieDto = objectMapper.readValue(movieDtoObject, MovieDto.class);
		return movieDto;	
	}
}
