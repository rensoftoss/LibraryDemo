package com.library.librarydemo.exception;

public class LibraryDemoException extends RuntimeException {

    public LibraryDemoException() {
        super("Unknown error encountered");
    }

    public LibraryDemoException(String message) {
        super(message);
    }
}
