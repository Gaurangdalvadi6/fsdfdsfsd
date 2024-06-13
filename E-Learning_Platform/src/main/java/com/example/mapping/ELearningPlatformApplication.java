package com.example.mapping;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.mapping.models.Author;
import com.example.mapping.repository.AuthorRepository;

@SpringBootApplication
public class ELearningPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(ELearningPlatformApplication.class, args);
	}

//	@Bean
	public CommandLineRunner commandLineRunner(AuthorRepository repository){
		return args -> {
			var author = Author.builder()
			.firstName("Gaurang")
			.lastName("Dalvadi")
			.email("gaurangdalvadi@gmail.com")
			.age(25)
			.build();
			repository.save(author);
		};
	}

}
