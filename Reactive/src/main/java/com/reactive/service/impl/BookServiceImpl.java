package com.reactive.service.impl;

import com.reactive.entities.Book;
import com.reactive.exception.BookNotFoundException;
import com.reactive.repository.BookRepository;
import com.reactive.service.BookService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Mono<Book> create(Book book) {
        Mono<Book> createBook = bookRepository.save(book);
        return createBook;
    }

    @Override
    public Flux<Book> getAll() {
        return bookRepository.findAll().map(book -> {
            book.setName(book.getName().toUpperCase());
            return book;
        });
    }

    @Override
    public Mono<Book> get(int bookId) {
        Mono<Book> item = bookRepository.findById(bookId)
                .switchIfEmpty(Mono.error(new BookNotFoundException("No book found with ID: " + bookId)));
        return item;
    }

    @Override
    public Mono<Book> update(Book book, int bookId) {
        Mono<Book> oldBook = bookRepository.findById(bookId)
                .switchIfEmpty(Mono.error(new BookNotFoundException("No book found with ID: " + bookId)));
        return oldBook.flatMap(book1 -> {

            book1.setName(book.getName());
            book1.setDescription(book.getDescription());
            book1.setPublisher(book.getPublisher());
            book1.setAuthor(book.getAuthor());
            return  bookRepository.save(book1);
        });
    }

    @Override
    public Mono<Void> delete(int bookId) {
        return bookRepository
                .findById(bookId)
                .switchIfEmpty(Mono.error(new BookNotFoundException("No book found with ID: " + bookId)))
                .flatMap(book -> bookRepository.delete(book));
//                .onErrorMap(e -> new RuntimeException("Error occurred while deleting book with ID: " + bookId, e));
    }

//    @Override
//    public Flux<Book> search(String query) {
//        return null;
//    }

    @Override
    public Flux<Book> searchBooks(String titleKeyword) {
        return bookRepository.searchBookByTitle("%"+titleKeyword+"%");
    }
}
