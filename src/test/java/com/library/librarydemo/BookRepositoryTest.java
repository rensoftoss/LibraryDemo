package com.library.librarydemo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.library.librarydemo.domain.Book;
import com.library.librarydemo.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
@Slf4j
public class BookRepositoryTest {

    public static final String AUTHOR = "author1";
    public static final String TITLE = "title1";

    @Autowired
    BookRepository bookRepository;

    private Book createBook() {
        return new Book(null, AUTHOR, TITLE);
    }

    @Test
    public void givenValue_whenFindByValue_thenFindBook() {
        Book bookInDb = bookRepository.save(createBook()).block();
        Mono<Book> bookMono = bookRepository.findById(bookInDb.getId());

        StepVerifier
            .create(bookMono)
            .assertNext(book -> {
                assertThat(book.getAuthor()).isEqualTo(AUTHOR);
                assertThat(book.getTitle()).isEqualTo(TITLE);
                assertNotNull(book.getId());
            })
            .expectComplete()
            .verify();
    }

    @Test
    public void givenBook_whenSave_thenSaveBook() {
        Mono<Book> bookMono = bookRepository.save(createBook());

        StepVerifier.create(bookMono)
                    .assertNext(book -> assertNotNull(book.getId()))
                    .expectComplete()
                    .verify();
    }
}
