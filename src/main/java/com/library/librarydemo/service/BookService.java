package com.library.librarydemo.service;

import com.library.librarydemo.domain.Book;
import com.library.librarydemo.exception.BookNotFoundException;
import com.library.librarydemo.repository.BookRepository;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BookService {

    private final BookRepository books;

    public BookService(BookRepository books) {
        this.books = books;
    }

    public Flux<Book> findAll() {
        return books.findAll();
    }

    public Mono<Book> save(Book book) {
        return books.save(book);
    }

    public Mono<Book> findById(String id) {
        return books.findById(id).switchIfEmpty(Mono.error(new BookNotFoundException()));
    }

    public Mono<Void> deleteById(String id) {
        return this.books.deleteById(id);
    }

}
