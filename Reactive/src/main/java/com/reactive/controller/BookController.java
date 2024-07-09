package com.reactive.controller;

import com.reactive.entities.Book;
import com.reactive.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

//    public BookController(BookService bookService) {
//        this.bookService = bookService;
//    }

    // create
    @PostMapping
    public Mono<Book> create(@RequestBody Book book){
        return  bookService.create(book);
    }

    // get all books
    @GetMapping
    public Flux<Book> getAll(){
        return  bookService.getAll();
    }

    // get book
    @GetMapping("/{bookId}")
    public  Mono<Book> getBook(@PathVariable int bookId){
        return  bookService.get(bookId);
    }

    // update book
    @PutMapping("/{bookId}")
    public  Mono<Book> updateBook(@RequestBody Book book,@PathVariable int bookId){
        return  bookService.update(book,bookId);
    }

    // delete book
    @DeleteMapping("/{bookId}")
    public Mono<Void> deleteBook(@PathVariable int bookId){
        return  bookService.delete(bookId);
    }

    @GetMapping("/search")
    public Flux<Book> searchBooks(@RequestParam("query") String query){
        return  bookService.searchBooks(query);
    }
}
