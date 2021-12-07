package com.library.librarydemo;

import static org.assertj.core.api.Assertions.assertThat;

import com.library.librarydemo.domain.Book;
import com.library.librarydemo.dto.BookDto;
import com.library.librarydemo.mapper.BookMapper;
import org.junit.jupiter.api.Test;

public class BookMapperTest {

    @Test
    public void shouldMapBookToBookDto() {
        //given
        String bookId = "book1";
        String bookAuthor = "author1";
        String bookTitle = "title1";

        Book book = new Book(bookId, bookAuthor, bookTitle);

        //when
        BookDto bookDto = BookMapper.INSTANCE.bookToBookDto(book);

        //then
        assertThat(bookDto).isNotNull();
        assertThat(bookDto.getId()).isEqualTo(bookId);
        assertThat(bookDto.getAuthor()).isEqualTo(bookAuthor);
        assertThat(bookDto.getTitle()).isEqualTo(bookTitle);
    }
}
