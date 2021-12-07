package com.library.librarydemo.exception;

public class BookNotFoundException extends LibraryDemoException {

    public BookNotFoundException() {
        super("Book not found");
    }
}
