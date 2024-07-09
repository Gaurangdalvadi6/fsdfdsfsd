package com.reactive.service;

import com.reactive.entities.Book;
import com.reactive.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
public class RepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void  findMethodTest(){

//        Flux<Book> name = bookRepository.findByAuthor("R. K. Narayan");
//        StepVerifier.create(name)
//                .expectNextCount(1)
//                .verifyComplete();

//        bookRepository.getAllBooksByAuthor("mahabharat","R. K. Narayan")
//                .as(StepVerifier::create)
//                .expectNextCount(1)
//                .verifyComplete();

        bookRepository.searchBookByTitle("%mahabharat%")
                .as(StepVerifier::create)
                .expectNextCount(1)
                .verifyComplete();

    }
}
