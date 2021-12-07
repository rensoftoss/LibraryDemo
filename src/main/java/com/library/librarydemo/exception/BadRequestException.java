package com.library.librarydemo.exception;

public class BadRequestException extends LibraryDemoException {

    public BadRequestException() {
        this("Bad request");
    }

    public BadRequestException(String message) {
        super(message);
    }
}
