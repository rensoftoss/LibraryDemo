package com.library.librarydemo.repository;

import com.library.librarydemo.domain.Book;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends ReactiveMongoRepository<Book, String> {
}