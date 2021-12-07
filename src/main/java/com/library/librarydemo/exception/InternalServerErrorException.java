package com.library.librarydemo.exception;

public class InternalServerErrorException extends LibraryDemoException {

    public InternalServerErrorException() {
        this("Unknown error");
    }

    public InternalServerErrorException(String message) {
        super(message);
    }
}
