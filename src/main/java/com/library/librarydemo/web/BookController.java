package com.library.librarydemo.web;

import com.library.librarydemo.domain.Book;
import com.library.librarydemo.dto.BookDto;
import com.library.librarydemo.exception.BookNotFoundException;
import com.library.librarydemo.mapper.BookMapper;
import com.library.librarydemo.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Flux<BookDto> all() {
        return this.bookService.findAll().map(BookMapper.INSTANCE::bookToBookDto);
    }

    @PostMapping(value = "",
                 consumes = MediaType.APPLICATION_JSON_VALUE,
                 produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Mono<BookDto>> create(@RequestBody BookDto bookDto) {
        Book book = BookMapper.INSTANCE.bookDtoToBook(bookDto);
        return ResponseEntity.ok().body(bookService.save(book).map(BookMapper.INSTANCE::bookToBookDto));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Mono<BookDto>> get(@PathVariable("id") String id) {
        return ResponseEntity.ok().body(bookService.findById(id)
                                                   .map(BookMapper.INSTANCE::bookToBookDto)
                                                   .onErrorResume(e -> {
                                                       throw new BookNotFoundException();
                                                   }));
    }

    @PutMapping(value = "/{id}",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Mono<BookDto>> update(@PathVariable("id") String id, @RequestBody BookDto bookDto) {
        return ResponseEntity.ok().body(bookService.findById(id)
                                                   .map(p -> {
                                                       p.setAuthor(bookDto.getAuthor());
                                                       p.setTitle(bookDto.getTitle());
                                                       return p;
                                                   })
                                                   .flatMap(this.bookService::save)
                                                   .map(BookMapper.INSTANCE::bookToBookDto));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> delete(@PathVariable("id") String id) {
        return bookService.deleteById(id);
    }

}