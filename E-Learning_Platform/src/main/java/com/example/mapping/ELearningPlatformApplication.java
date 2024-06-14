package com.example.mapping;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.mapping.models.Author;
import com.example.mapping.repository.AuthorRepository;
import com.github.javafaker.Faker;

@SpringBootApplication
public class ELearningPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(ELearningPlatformApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AuthorRepository repository) {
		return args -> {
//			for (int i = 0; i < 50; i++) {
//				Faker faker = new Faker();
//				var author = Author.builder().firstName(faker.name().firstName()).lastName(faker.name().lastName())
//						.email(faker.internet().emailAddress()).age(faker.number().numberBetween(10, 50)).build();
//				repository.save(author);
//			}

			// update user
//			var author = Author.builder()
//					.id(1)
//					.firstName("gautam")
//					.lastName("chauhan")
//					.email("gautam@gmail.com")
//					.age(25)
//					.build();
//			repository.save(author);

			// update Author a set a.age= :age where a.id= :id
			// repository.updateAuthor(1, 23);

			// update all authors
//			 repository.updateAllAuthors(25);
			
		
		};
	}
}
