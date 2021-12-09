package com.library.librarydemo;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import com.library.librarydemo.domain.Book;
import com.library.librarydemo.dto.BookDto;
import com.library.librarydemo.exception.BookNotFoundException;
import com.library.librarydemo.mapper.BookMapper;
import com.library.librarydemo.service.BookService;
import com.library.librarydemo.web.BookController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@WebFluxTest(BookController.class)
public class BookControllerTest {

    public static final String AUTHOR = "author1";
    public static final String TITLE = "title1";

    @Autowired
    private WebTestClient webClient;

    @MockBean
    private BookService bookService;

    private BookDto createBookDto() {
        return BookDto.builder().author(AUTHOR).title(TITLE).build();
    }

    @Test
    public void shouldGetBook() {
        BookDto bookDto = createBookDto();

        when(bookService.findById(any(String.class))).thenReturn(Mono.just(BookMapper.INSTANCE.bookDtoToBook(bookDto)));

        webClient.get()
                 .uri("/books/1")
                 .exchange()
                 .expectStatus()
                 .isOk()
                 .expectBody()
                 .jsonPath("author").isEqualTo(AUTHOR)
                 .jsonPath("title").isEqualTo(TITLE);
    }

    @Test
    public void shouldGetBookNotFound() {
        when(bookService.findById(any(String.class))).thenThrow(new BookNotFoundException());

        webClient.get()
                 .uri("/books/1")
                 .exchange()
                 .expectStatus()
                 .isEqualTo(NOT_FOUND);
    }

    @Test
    public void shouldCreate() {
        BookDto bookDto = createBookDto();

        when(bookService.save(any(Book.class))).thenReturn(Mono.just(BookMapper.INSTANCE.bookDtoToBook(bookDto)));

        webClient.post()
                 .uri("/books")
                 .bodyValue(bookDto)
                 .exchange()
                 .expectStatus()
                 .isOk()
                 .expectBody()
                 .jsonPath("author").isEqualTo(AUTHOR)
                 .jsonPath("title").isEqualTo(TITLE);
    }

}